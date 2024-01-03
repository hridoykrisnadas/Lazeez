package com.hridoykrisna.Lazeez.service.impl;

import com.hridoykrisna.Lazeez.Utils.CommonUtils;
import com.hridoykrisna.Lazeez.model.CartItem;
import com.hridoykrisna.Lazeez.model.FoodMenu;
import com.hridoykrisna.Lazeez.repository.CartRepo;
import com.hridoykrisna.Lazeez.repository.FoodMenuRepo;
import com.hridoykrisna.Lazeez.service.CartService;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceIMPL implements CartService {
    private final FoodMenuRepo foodMenuRepo;
    private final CartRepo cartRepo;
    @Override
    public List<CartItem> addToCart(int id) {
        Optional<FoodMenu> foodMenu = foodMenuRepo.findById(id);
        if (foodMenu.isPresent()){
            CartItem cartItem = new CartItem();
            cartItem.setFood_id(id);
            cartItem.setName(foodMenu.get().getName());
            cartItem.setDescription(foodMenu.get().getDescription());
            cartItem.setUnit_price(foodMenu.get().getPrice());
            cartItem.setImagePath(foodMenu.get().getImagePath());
            cartItem.setStatus(1);
            cartItem.setQuantity(1);
            cartItem.setTotal_price(foodMenu.get().getPrice());
            cartItem.setCreatedBy(CommonUtils.user.getId());

            List<CartItem> cartItems = cartRepo.getCartListAndStatusOne(CommonUtils.user.getId()); // Get all at once

            Optional<CartItem> existingItem = cartItems.stream()
                    .filter(item -> item.getFood_id() == id)
                    .findFirst();

            if (existingItem.isPresent()) {
                existingItem.get().setUpdateBy(CommonUtils.user.getId());
                existingItem.get().setQuantity(existingItem.get().getQuantity() + 1);
                existingItem.get().setTotal_price(existingItem.get().getTotal_price() + cartItem.getUnit_price());
                cartRepo.save(existingItem.get());
            } else {
                // Add new item
                cartRepo.save(cartItem);
            }
        }
        return cartRepo.getCartListAndStatusOne(CommonUtils.user.getId());
    }
}
