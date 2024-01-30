package com.projektowanie.service;

import com.projektowanie.model.*;
import com.projektowanie.repository.DiscountRepository;
import com.projektowanie.repository.ProductInCartRepository;
import com.projektowanie.repository.ProductRepository;
import com.projektowanie.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductInCartRepository productInCartRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private DiscountRepository discountRepository;

    public void seedShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setShoppingCartId(1L);
        shoppingCart.setTotalPrice(BigDecimal.ZERO);
        shoppingCart.setProductsInCart(new ArrayList<>());

        Optional<Product> existingChleb = productRepository.findByProductName("Chleb");
        Product chleb = existingChleb.orElseGet(() -> {
            Product newChleb = new Product();
            newChleb.setProductName("Chleb");
            newChleb.setPrice(BigDecimal.valueOf(5.30));
            newChleb.setImageName("chleb.jpeg");
            newChleb.setProductsInCart(new ArrayList<>());
            return productRepository.save(newChleb);
        });

        Optional<Product> existingMleko = productRepository.findByProductName("Mleko");
        Product mleko = existingMleko.orElseGet(() -> {
            Product newMleko = new Product();
            newMleko.setProductName("Mleko");
            newMleko.setPrice(BigDecimal.valueOf(3.30));
            newMleko.setImageName("mleko.jpg");
            newMleko.setProductsInCart(new ArrayList<>());
            return productRepository.save(newMleko);
        });

        shoppingCart= shoppingCartRepository.save(shoppingCart);

        ProductInCartId product1InCartId = new ProductInCartId(chleb.getProductId(), shoppingCart.getShoppingCartId());
        ProductInCartId product2InCartId = new ProductInCartId(mleko.getProductId(), shoppingCart.getShoppingCartId());

        ProductInCart product1InCart = new ProductInCart();
        ProductInCart product2InCart = new ProductInCart();

        product1InCart.setId(product1InCartId);
        product1InCart.setProduct(chleb);
        product1InCart.setShoppingCart(shoppingCart);
        product1InCart.setQuantity(1);
        product1InCart.setProductInCartTotalPrice(chleb.getPrice());

        product2InCart.setId(product2InCartId);
        product2InCart.setProduct(mleko);
        product2InCart.setShoppingCart(shoppingCart);
        product2InCart.setQuantity(1);
        product2InCart.setProductInCartTotalPrice(mleko.getPrice());

        product1InCart = productInCartRepository.save(product1InCart);
        product2InCart = productInCartRepository.save(product2InCart);

        chleb.getProductsInCart().add(product1InCart);
        mleko.getProductsInCart().add(product2InCart);
        shoppingCart.getProductsInCart().add(product1InCart);
        shoppingCart.getProductsInCart().add(product2InCart);

        productRepository.save(chleb);
        productRepository.save(mleko);
        shoppingCart.setTotalPrice(calculateTotalPrice(shoppingCart.getProductsInCart()));
        shoppingCartRepository.save(shoppingCart);

        Discount existingDiscount1 = discountRepository.findByName("Nie wybrano");
        if(existingDiscount1 == null) {
            Discount discount1 = new Discount();
            discount1.setName("Nie wybrano");
            discount1.setDiscountAmount(new BigDecimal("0"));
            discountRepository.save(discount1);
        }

        Discount existingDiscount2 = discountRepository.findByName("WIOSNA10");
        if (existingDiscount2 == null) {
            Discount discount2 = new Discount();
            discount2.setName("WIOSNA10");
            discount2.setDiscountAmount(new BigDecimal("10"));
            discountRepository.save(discount2);
        }

        Discount existingDiscount3 = discountRepository.findByName("WIOSNA20");
        if (existingDiscount3 == null) {
            Discount discount3 = new Discount();
            discount3.setName("WIOSNA20");
            discount3.setDiscountAmount(new BigDecimal("20"));
            discountRepository.save(discount3);
        }
    }

    @Transactional
    public ShoppingCart getShoppingCartWithProducts() {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findShoppingCartWithProductsById(1L);
        ShoppingCart shoppingCart = optionalShoppingCart.orElse(new ShoppingCart());

        if (shoppingCart.getProductsInCart() == null) {
            shoppingCart.setProductsInCart(new ArrayList<>());
        }

        return shoppingCart;
    }

    @Transactional
    public void removeProductFromCart(Long productId) {
        ProductInCartId productInCartId = new ProductInCartId(productId, 1L);
        Optional<ProductInCart> productInCartOptional = productInCartRepository.findById(productInCartId);
        productInCartOptional.ifPresent(productInCart -> {
            ShoppingCart shoppingCart = productInCart.getShoppingCart();
            shoppingCart.getProductsInCart().remove(productInCart);
            shoppingCart.setTotalPrice(calculateTotalPrice(shoppingCart.getProductsInCart()));
            shoppingCartRepository.save(shoppingCart);
            productInCartRepository.delete(productInCart);
        });
    }

    private BigDecimal calculateTotalPrice(List<ProductInCart> productsInCart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ProductInCart productInCart : productsInCart) {
            BigDecimal productPrice = productInCart.getProduct().getPrice();
            int quantity = productInCart.getQuantity();
            totalPrice = totalPrice.add(productPrice.multiply(BigDecimal.valueOf(quantity)));
        }
        return totalPrice;
    }

    @Transactional
    public void increaseProductQuantity(Long productId) {
        updateProductQuantity(productId, 1);
    }

    @Transactional
    public void decreaseProductQuantity(Long productId) {
        updateProductQuantity(productId, -1);
    }

    @Transactional
    public void updateProductQuantity(Long productId, int quantityChange) {
        Optional<ProductInCart> productInCartOptional = productInCartRepository.findById(new ProductInCartId(productId, 1L));

        productInCartOptional.ifPresent(productInCart -> {
            int newQuantity = productInCart.getQuantity() + quantityChange;

            if (newQuantity > 0) {
                productInCart.setQuantity(newQuantity);
                productInCart.setProductInCartTotalPrice(productInCart.getProduct().getPrice().multiply(BigDecimal.valueOf(productInCart.getQuantity())));
                ShoppingCart shoppingCart = productInCart.getShoppingCart();
                shoppingCart.setTotalPrice(calculateTotalPrice(shoppingCart.getProductsInCart()));
                shoppingCartRepository.save(shoppingCart);
                productInCartRepository.save(productInCart);
            } else {
                removeProductFromCart(productId);
            }
        });
    }

}
