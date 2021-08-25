package com.pricemonitor;

import com.pricemonitor.entity.*;
import com.pricemonitor.repositories.*;
import com.pricemonitor.repositories.impl.*;
import com.pricemonitor.spring.ApplicationConfiguration;
import com.pricemonitor.tools.JSONConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        User user = new User();
        user.setLogin("Test user");
        user.setEmail("email.ru");
        user.setPassword("lskdjlfkjslkdjflksjdlkfjlskdjlfksjdlfwoieuroweiuoivboiuxo");
        Role role = new Role();
        role.setName("ROLE_MERCHANT");
        java.util.List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        //----

        Product product = new Product();
        product.setName("Булка");
        product.setBoxing("шт.");
        Price price = new Price();
        price.setMoney("rub");
        price.setTotal(25);
        product.setPrice(price);
        Category category = new Category();
        category.setName("Бакалея");
        product.setCategory(category);

        // ----

        Merchant merchant = new Merchant();
        merchant.setName("Торговый дом МОЛОКО");
        merchant.setAddress("г. Орёл, ул. Горького, 54");

        // ----

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        UserRepository repository = (UserRepository) context.getBean(IUserRepository.class);
        java.util.List<User> users = repository.getAllUsers();

        ProfileRepository profileRepository = (ProfileRepository) context.getBean(IProfileRepository.class);
        java.util.List<Profile> profiles = profileRepository.getAllProfiles();

        ProductRepository productRepository = (ProductRepository) context.getBean(IProductRepository.class);
        java.util.List<Product> productList = productRepository.findAllProduct();

        MerchantRepository merchantRepository = (MerchantRepository) context.getBean(IMerchantRepository.class);
        /*Merchant usemerch = merchantRepository.findMerchantById(2);
        usemerch.setProductList(productList);
        merchantRepository.updateMerchant(usemerch);*/
        java.util.List<Merchant> merchantList = merchantRepository.findAllMerchant();

        //merchantRepository.createMerchant(merchant);


        DynamicPriceRepository dynamicPriceRepository = new DynamicPriceRepository();
        java.util.List<DynamicPrice> dynamicPriceList = dynamicPriceRepository.findAllDynamicPriceList();

        System.out.println(" - - - -  USERS - - - - - - - ");

        for (User u: users) {
            System.out.println("ID: "+u.getId()+"| Login:"+u.getLogin()+"| Password:"+u.getPassword()+" | Email:"+u.getEmail());
            System.out.println("Role of "+u.getLogin()+" is");
            for (Role r:u.getRoles()) {
                System.out.println(r.getName());
            }
        }

        System.out.println(" - - - - PROFILES - - - - - - - ");

        for (Profile p: profiles) {
            System.out.println("ID: "+p.getId()+"| Full Name:"+p.getFullName()+"| Login:"+p.getUser().getLogin()+"| Email:"+p.getUser().getEmail()+"| Birth Day:"+p.getBirthDay().getCalendarType());
        }

        System.out.println(" - - - - - PRODUCTS - - - - - - ");

        for (Product p:productList) {
            System.out.println(p.getId()+" "+p.getName()+" "+p.getBoxing()+" "+p.getPrice().getMoney()+" "+p.getPrice().getTotal()+" "+p.getCategory().getName());
        }

        System.out.println(" - - - - - MERCHANTS - - - - - - ");

        for (Merchant m: merchantList) {
            System.out.println(m.getId()+" "+m.getName()+" "+m.getAddress()+" ");
            java.util.List<Product> temp = m.getProductList();
            for (Product pp:temp) {
                System.out.println(pp.getId()+" "+pp.getName()+" ОТДЕЛ:"+pp.getCategory().getName());
            }
        }

        System.out.println(" - - - - - DYNAMIC PRICE PRODUCT - - - - - - ");

        for (DynamicPrice dp: dynamicPriceList) {
            System.out.println(dp.getId()+" "+dp.getProduct().getName()+" "+dp.getProduct().getCategory().getName()+" "+dp.getPrice().getMoney()+" "+dp.getPrice().getTotal());
        }


    }
}
