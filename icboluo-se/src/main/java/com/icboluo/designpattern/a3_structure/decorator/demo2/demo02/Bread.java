package com.icboluo.designpattern.a3_structure.decorator.demo2.demo02;

//面包类
 class Bread extends Food {

   private Food basicFood;

   public Bread(Food basicFood) {
       this.basicFood = basicFood;
   }

   @Override
   public String make() {
       return basicFood.make()+"+面包";
   }
}




