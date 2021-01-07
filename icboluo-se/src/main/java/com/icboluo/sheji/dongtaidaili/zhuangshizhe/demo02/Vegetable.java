package com.icboluo.sheji.dongtaidaili.zhuangshizhe.demo02;

//蔬菜类
 class Vegetable extends Food {

   private Food basic_food;

   public Vegetable(Food basic_food) {
       this.basic_food = basic_food;
   }

   @Override
   public String make() {
       return basic_food.make()+"+蔬菜";
   }

}