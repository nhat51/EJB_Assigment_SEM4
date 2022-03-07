package com.example.emybank.service;

import com.example.emybank.constant.ErrorCode;
import com.example.emybank.dto.TransactionResult;
import com.example.emybank.entity.User;
import com.example.emybank.exception.CheckBalanceException;
import com.example.emybank.exception.OverDraftException;
import com.example.emybank.exception.SystemException;
import com.example.emybank.exception.UserNotExistException;
import com.example.emybank.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;

public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.userBalance}")
    private String retrieveUserBalanceUrl;

    public User retrieveBalances(int id) {
        User user = userRepository.findByUserId(id)
                .orElseThrow(() -> new UserNotExistException("Account with id:" + id + " does not exist.", ErrorCode.ACCOUNT_ERROR, HttpStatus.NOT_FOUND));

        return user;
    }

    @Transactional
    public void transferBalances(TransactionResult transactionHistory) throws OverDraftException, UserNotExistException, SystemException {
        User userFrom = userRepository.getUserForUpdate(Math.toIntExact(transactionHistory.getSender_id()))
                .orElseThrow(() -> new UserNotExistException("User with id:" + transactionHistory.getSender_id() + " does not exist.", ErrorCode.ACCOUNT_ERROR));

        User userTo = userRepository.getUserForUpdate(Math.toIntExact(transactionHistory.getReceiver_id()))
                .orElseThrow(() -> new UserNotExistException("User with id:" + transactionHistory.getReceiver_id() + " does not exist.", ErrorCode.ACCOUNT_ERROR));

        if(userFrom.getBalance().compareTo(transactionHistory.getAmount()) < 0) {
            throw new OverDraftException("Account with id:" + userFrom.getId() + " does not have enough balance to transfer.", ErrorCode.ACCOUNT_ERROR);
        }

        userFrom.setBalance(userFrom.getBalance().subtract(transactionHistory.getAmount()));
        userTo.setBalance(userTo.getBalance().add(transactionHistory.getAmount()));
    }

    public BigDecimal checkBalance(Long userId) throws SystemException {

        try {
            String url = retrieveUserBalanceUrl.replace("{id}", userId.toString());

            log.info("checking balance from "+url);

            ResponseEntity<User> balanceCheckResult = restTemplate.getForEntity(url, User.class);

            if(balanceCheckResult.getStatusCode().is2xxSuccessful()) {
                if(balanceCheckResult.hasBody()) {
                    return balanceCheckResult.getBody().getBalance();
                }
            }
        } catch (ResourceAccessException ex) {
            final String errorMessage = "Encounter timeout error, please check with system administrator.";

            if(ex.getCause() instanceof SocketTimeoutException) {
                throw new CheckBalanceException(errorMessage, ErrorCode.TIMEOUT_ERROR);
            }
        }
        // for any other fail cases
        throw new SystemException("Encounter internal server error, please check with system administrator.", ErrorCode.SYSTEM_ERROR);
    }
}
