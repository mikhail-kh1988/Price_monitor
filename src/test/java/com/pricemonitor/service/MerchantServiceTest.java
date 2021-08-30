package com.pricemonitor.service;

import com.pricemonitor.ContextConfigurationTest;
import com.pricemonitor.JPAConfigureTest;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.repositories.IMerchantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ContextConfigurationTest.class, JPAConfigureTest.class})
class MerchantServiceTest {

    @Autowired
    private IMerchantRepository merchantRepository;

    @InjectMocks
    private MerchantService merchantService;

    @BeforeEach
    void setUp() {
        merchantService = mock(MerchantService.class);
    }

    @Test
    void whenCreateNewMerchant() {
        Merchant merchant = new Merchant();
        merchant.setName("test");

        int countMerchantBeforeInsert = merchantRepository.findAllMerchant().size();

        merchantService.createNewMerchant(merchant);

        verify(merchantService, times(1)).createNewMerchant(merchant);

        assertEquals(countMerchantBeforeInsert, merchantRepository.findAllMerchant().size());
    }

    @Test
    void whenUpdateMerchant() {

        Merchant merchant = merchantRepository.findMerchantById(2);

        merchant.setName("test");

        merchantService.updateMerchant(merchant);

        verify(merchantService, times(1)).updateMerchant(merchant);
    }

    @Test
    void whenDeleteMerchant() {
        Merchant merchant = merchantRepository.findMerchantById(2);

        int countMerchantBeforeDelete = merchantRepository.findAllMerchant().size();

        merchantService.deleteMerchant(merchant);

        verify(merchantService, times(1)).deleteMerchant(merchant);

        assertEquals(countMerchantBeforeDelete, merchantRepository.findAllMerchant().size());
    }
}