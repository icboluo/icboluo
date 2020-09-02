package com.icboluo.designpattern.dongtaidaili.zhuangshizhe.demo02;

import lombok.NoArgsConstructor;

@NoArgsConstructor
 class Food {

   private String foodName;


   public Food(String foodName) {
       this.foodName = foodName;
   }

   public String make() {
       return foodName;
   };
}