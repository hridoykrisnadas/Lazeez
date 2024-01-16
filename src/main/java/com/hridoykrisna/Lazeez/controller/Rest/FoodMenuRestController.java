package com.hridoykrisna.Lazeez.controller.Rest;

import com.hridoykrisna.Lazeez.Utils.CommonUtils;
import com.hridoykrisna.Lazeez.model.CartItem;
import com.hridoykrisna.Lazeez.repository.CartRepo;
import com.hridoykrisna.Lazeez.service.CartService;
import com.hridoykrisna.Lazeez.service.FoodMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/food-menu/")
public class FoodMenuRestController {
    private final FoodMenuService foodMenuService;

    @GetMapping("status/{food-id}/{status}")
    public int addCartItem(@PathVariable("food-id") int id, @PathVariable("status") int status) {
       return foodMenuService.changeFoodStatus(id, status);
    }
}
