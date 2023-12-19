package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("/showProductsByPerson id_покупателя - посмотреть какие товары покупал клиент;\n" +
                "/findPersonsByProductTitle название_товара - посмотреть какие клиенты купили определенный товар;\n" +
                "/removeBuyer (removeProduct) имя_элемента - удалить из базы товар/покупателя\n" +
                "/buy имя_покупателя название_товара - купить определенный товар.\n" +
                "Введите команду:");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Buyers.class)
                .addAnnotatedClass(Purchase.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        if (command.contains("/showProductsByPerson ")) {
            showProducts4(factory, Integer.parseInt(command.replace("/showProductsByPerson ", "")));
        } else if (command.contains("/findPersonsByProductTitle ")) {
            findPersons2(factory, command.replace("/findPersonsByProductTitle ", ""));
        } else if (command.contains("/removeProduct ")) {
            removeProduct(factory, command.replace("/removeProduct ", ""));
        } else if (command.contains("/removeBuyer ")){
            removeBuyer(factory, command.replace("/removeBuyer ", ""));
        }else if (command.contains("/buy ")) {
            buy(factory, command.replace("/buy ", ""));
        } else {
            System.out.println("Введена неверная команда!");
        }
        factory.close();
        session.close();
    }

//    public static void showProducts2(SessionFactory factory, String buyerName) {
//        System.out.println("Список товаров, который покупал(а) " + buyerName + ":");
//        Session session = factory.getCurrentSession();
//        session.beginTransaction();
//        // сделать без Query
//        Query q = session.createQuery("from Purchase where id > 0");
//        List result = q.list();
//        boolean check = false;
//        session.getTransaction().commit();
//        for (int i = 1; i <= result.size(); i++) {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Purchase purchase = session.get(Purchase.class, i);
//            Buyers buyer = purchase.getBuyer();
//            if (buyer.getName().equals(buyerName)) {
//                System.out.println(purchase.getProducts().getTitle() + " за " + purchase.price + "₽");
//                check = true;
//            }
//            session.getTransaction().commit();
//        }
//        if (!check){
//            System.out.println("Данный покупатель ничего не приобретал.");
//        }
//    }

    public static void showProducts4(SessionFactory factory, int buyerId) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Buyers buyer = session.get(Buyers.class, buyerId);
        System.out.println("Список товаров, который покупал(а) " + buyer);
        if (buyer != null) {
            List<Purchase> purchases = buyer.getPurchases();
            if (!purchases.isEmpty()) {
                for (Purchase p : purchases) {
                    System.out.println(p);
                }
            } else {
                System.out.println("Данный покупатель ничего не приобретал.");
            }
        } else {
            System.out.println("Покупатель с id " + buyerId + " не найден.");
        }
        session.getTransaction().commit();
    }

    public static void findPersons2(SessionFactory factory, String product) {
        System.out.println("Список покупателей, которые приобрели " + product + ":");
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery("from Purchase where id > 0");
        List result = q.list();
        boolean check = false;
        session.getTransaction().commit();
        for (int i = 1; i <= result.size(); i++) {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Purchase purchase = session.get(Purchase.class, i);
            Product product1 = purchase.getProducts();
            if (product1.getTitle().equals(product)) {
                System.out.println(purchase.getBuyer().getName());
                check = true;
            }
            session.getTransaction().commit();
        }
        if (!check){
            System.out.println("Данный товар никто не приобретал");
        }
    }

    public static void removeProduct(SessionFactory factory, String productName) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Query getProductQuery = session.createQuery("from Product where title = :title");
            getProductQuery.setParameter("title", productName);
            Product product = (Product) getProductQuery.uniqueResult();

            if (product != null) {
                Query deletePurchaseQuery = session.createQuery("delete from Purchase where product_id = :productId");
                deletePurchaseQuery.setParameter("productId", product.getId());
                deletePurchaseQuery.executeUpdate();
                session.delete(product);
            }
            session.getTransaction().commit();
            System.out.println("Товар " + productName + " успешно удалён из базы данных");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //session.close();
        }
    }

    public static void removeBuyer(SessionFactory factory, String person) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            Query getBuyerQuery = session.createQuery("from Buyers where name = :buyer");
            getBuyerQuery.setParameter("buyer", person);
            Buyers buyer = (Buyers) getBuyerQuery.uniqueResult();

            if (buyer != null) {
                Query deletePurchaseQuery = session.createQuery("delete from Purchase where buyer_id = :personId");
                deletePurchaseQuery.setParameter("personId", buyer.getId());
                deletePurchaseQuery.executeUpdate();
                session.delete(buyer);
            }

            session.getTransaction().commit();
            System.out.println("Покупатель " + person + " успешно удалён из базы данных");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void buy(SessionFactory factory, String command) {
        String[] a = command.split(" ");
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Buyers p0 = null;
        Product p1 = null;
        Query personQuery = session.createQuery("FROM Buyers WHERE name = :personName");
        personQuery.setParameter("personName", a[0]);
        List<Buyers> buyers = personQuery.list();
        if (!buyers.isEmpty()) {
            p0 = buyers.get(0);
        }
        Query productQuery = session.createQuery("FROM Product WHERE title = :productTitle");
        productQuery.setParameter("productTitle", a[1]);
        List<Product> products = productQuery.list();
        if (!products.isEmpty()) {
            p1 = products.get(0);
        }
        if (p0 != null && p1 != null) {
            Purchase purchase = new Purchase();
            purchase.setBuyer(p0);
            purchase.setProducts(p1);
            purchase.setPrice(p1.getPrice());
            session.save(purchase);
            System.out.println(a[0] + " успешно купил(а) " + a[1]);
        } else {
            System.out.println("Такой покупатель (или товар) не найден в базе данных!");
        }
        session.getTransaction().commit();
    }

    public static void addBuyer(SessionFactory factory, String name){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Buyers buyers = new Buyers();
        buyers.setName(name);
        session.save(buyers);
        System.out.println("Покупатель успешно добавлен. Его ID в БД: " + buyers.getId());
        session.getTransaction().commit();
    }

    public static void addProduct(SessionFactory factory, String name, int price){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = new Product();
        product.setTitle(name);
        product.setPrice(price);
        session.save(product);
        System.out.println("Товар успешно добавлен. Его ID в БД: " + product.getId());
        session.getTransaction().commit();
    }
    public static void addPurchase(SessionFactory factory, int buyerID, int productID){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Purchase purchase = new Purchase();
        Buyers buyer = session.get(Buyers.class, buyerID);
        Product product = session.get(Product.class, productID);
        if(product == null || buyer == null){
            System.out.println("Покупатель (или товар) с таким ID не найден в БД!");
            return;
        }
        purchase.setBuyer(buyer);
        purchase.setProducts(product);
        purchase.setPrice(product.getPrice());
        session.save(purchase);
        session.getTransaction().commit();
    }
}
