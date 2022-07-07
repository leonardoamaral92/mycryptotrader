package com.lmarques.mycryptotrader.services;

import com.lmarques.mycryptotrader.model.Investor;
import com.lmarques.mycryptotrader.model.Roles;
import com.lmarques.mycryptotrader.model.User;
import com.lmarques.mycryptotrader.model.authentication.Permission;
import com.lmarques.mycryptotrader.model.dto.APIResponse;
import com.lmarques.mycryptotrader.model.dto.DepositDTO;
import com.lmarques.mycryptotrader.model.dto.InvestorDTO;
import com.lmarques.mycryptotrader.model.dto.StatusResponse;
import com.lmarques.mycryptotrader.repository.InvestorRepository;
import com.lmarques.mycryptotrader.repository.PermissionRepository;
import com.lmarques.mycryptotrader.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.*;

@Service
public class InvestorService {
    @Autowired
    InvestorRepository investorRepository;

    @Autowired
    UserService userService;

    public APIResponse deposit(DepositDTO depositRequest) {
        Investor investor = investorRepository.findByUserId(depositRequest.getUserId()).get();
        investor.setFunds(depositRequest.getDepositValue()+investor.getFunds());
        Investor investorSaved = investorRepository.save(investor);
        DepositDTO depositDTO = DepositDTO.builder()
                .userId(investorSaved.getUser().getId())
                .depositValue(depositRequest.getDepositValue())
                .newFunds(investorSaved.getFunds())
                .build();

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .data(depositDTO)
                .build();
    }

    public APIResponse getFunds(Long userId) {
        Investor investor = investorRepository.findByUserId(userId).get();

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .data(investor.getFunds())
                .build();
    }

    public APIResponse create(InvestorDTO investorDTO) {

        User userSaved = null;
        //Saber se o investidor/user já existe
        //TODO TESTAR
        try {
            UserDetails user = userService.loadUserByUsername(investorDTO.getEmail());

            if(user != null)
                return APIResponse.builder()
                        .status(StatusResponse.FAILED)
                        .message("Usuário já existente.")
                        .data(investorDTO)
                        .build();
        }
        catch (UsernameNotFoundException e){
            userSaved = userService.create(investorDTO.getFullName(), investorDTO.getEmail(),
                    investorDTO.getPassword(), Set.of(Roles.COMMON_USER.name()));
        }
        //TODO Ver se o password já está sendo encriptado e escolher o melhor lugar de fazer isso

        return APIResponse.builder()
                .message("Usuário criado com sucesso")
                .data(userSaved)
                .build();
    }
}
