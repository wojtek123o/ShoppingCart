package com.projektowanie.service;

import com.projektowanie.model.*;
import com.projektowanie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Transactional
    public void placeOrder(CustomerOrder customerOrder, ShoppingCart shoppingCart, Shipping shipping) {
        Payment payment = new Payment();
//        Shipping shipping = new Shipping();
        payment.setPaymentType(customerOrder.getPayment().getPaymentType());
//        shipping.setShippingType(customerOrder.getShipping().getShippingType());
//        shipping.setShippingCity(customerOrder.getShipping().getShippingCity());
//        shipping.setShippingStreet(customerOrder.getShipping().getShippingStreet());
//        shipping.setShippingStreetNumber(customerOrder.getShipping().getShippingStreetNumber());
//        shipping.setShippingHouseNumber(customerOrder.getShipping().getShippingHouseNumber());
//        shipping.setShippingPostalCode(customerOrder.getShipping().getShippingPostalCode());

        BigDecimal shippingPrice = calculateShippingCost(shipping.getShippingType());
        shipping.setShippingPrice(shippingPrice);
        shippingRepository.save(shipping);
        paymentRepository.save(payment);
        customerOrder.setShipping(shipping);
        customerOrder.setPayment(payment);

        Discount discount = discountRepository.findByName(customerOrder.getDiscount().getName());
        customerOrder.setDiscount(discount);

        BigDecimal shippingCost = calculateShippingCost(customerOrder.getShipping().getShippingType());
        BigDecimal shoppingCartPrice = shoppingCart.getTotalPrice();
        BigDecimal discountTotalPrice = shoppingCartPrice.multiply((customerOrder.getDiscount().getDiscountAmount())
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
        );
        BigDecimal totalOrderPrice = shippingCost.add(shoppingCartPrice).subtract(discountTotalPrice);
//        customerOrder.setShoppingCart(shoppingCart);
        customerOrder.setDiscountTotalPrice(discountTotalPrice);
        customerOrder.setOrderTotalPrice(totalOrderPrice);

        customerOrderRepository.save(customerOrder);
    }

    private BigDecimal calculateShippingCost(ShippingType shippingType) {
        return switch (shippingType) {
            case COURIER -> new BigDecimal("20.00");
            case POST_OFFICE -> new BigDecimal("15.00");
        };
    }

    public CustomerOrder getOrderById(Long orderId) {
        return customerOrderRepository.findById(orderId).orElse(null);
    }

    public List<Discount> getAvailableDiscounts() {
        return discountRepository.findAll();
    }
}
