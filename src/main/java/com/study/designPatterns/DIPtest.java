package com.study.designPatterns;

/**
 * @author Hank
 * @Date 2019年05月22日
 */
public class DIPtest {

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.shopping(new ShaoguanShop());
        customer.shopping(new WuyuanShop());
    }

}

interface Shop {
    String sell();
}


class ShaoguanShop implements Shop {
    @Override
    public String sell() {
        return "韶关土特产：香菇、木耳……";
    }
}

class WuyuanShop implements Shop {
    @Override
    public String sell() {
        return "婺源土特产：绿茶、酒糟鱼……";
    }
}

class Customer {

    public void shopping(Shop shop) {
        System.out.println(shop.sell());
    }
}
