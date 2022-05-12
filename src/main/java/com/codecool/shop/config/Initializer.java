package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {
    private ReadConfig config;
    private DaoManager daoManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        daoManager = DaoManager.getInstance();

        config = new ReadConfig("connection.properties");

        if (config.getProperty().getProperty("dao").equals("memory")) {
            setMemoryUse();
            return;
        } else if (config.getProperty().getProperty("dao").equals("jdbc")) {
            setJDBC();
            return;
        }
        throw new IllegalArgumentException("Can't read the property file properly!");
    }

    private void setJDBC() {
        DBManager dbManager = new DBManager(config);
        dbManager.run();

        ProductCategoryDao productCategoryDataStore = dbManager.getProductCategoryDao();
        SupplierDao supplierDataStore = dbManager.getSupplierDao();
        ProductDao productDataStore = dbManager.getProductDao();
        UserDao userDao = dbManager.getUserDao();

        daoManager.addDao(productDataStore, productCategoryDataStore, supplierDataStore, userDao);
    }

    private void setMemoryUse() {
        ProductDao productDao = new ProductDaoMem();
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoMem();
        SupplierDao supplierDao = new SupplierDaoMem();


        //setting up a new supplier
        Supplier rubberFactory = new Supplier("Rubber Factory", "Everything from rubber!");
        Supplier quackQuack = new Supplier("QuackQuack", "Need a duck? Order from us!");
        supplierDao.add(rubberFactory);
        supplierDao.add(quackQuack);

        //setting up a new product category
        ProductCategory top = new ProductCategory("Best selling ducks", "Duck", "Best of the best");
        ProductCategory simpleColors = new ProductCategory("One-colour rubber ducks", "Duck", "Simple colored rubber ducks, all-time favorites.");
        ProductCategory starWars = new ProductCategory("Star Wars collection", "Duck", "Enjoy the magic corporation of QuackOut X Star Wars.");
        ProductCategory rockStar = new ProductCategory("Rock Stars collection", "Duck", "Famous rock stars, like never seen before.");
        productCategoryDao.add(top);
        productCategoryDao.add(simpleColors);
        productCategoryDao.add(starWars);
        productCategoryDao.add(rockStar);

        //top 3
        productDao.add(new Product("Yellow", 3.90f, "USD", "Best quality, lowest price", top, rubberFactory));
        productDao.add(new Product("EltonDuck", 5.90f, "USD", "Elton John rubber duck", top, quackQuack));
        productDao.add(new Product("Blue", 3.90f, "USD", "Best quality, lowest price", top, rubberFactory));

        //simple color
        productDao.add(new Product("Yellow", 3.90f, "USD", "Best quality, lowest price", simpleColors, rubberFactory));
        productDao.add(new Product("Blue", 3.90f, "USD", "Best quality, lowest price", simpleColors, rubberFactory));
        productDao.add(new Product("Green", 4.20f, "USD", "Best quality, lowest price", simpleColors, rubberFactory));
        productDao.add(new Product("Purple", 3.90f, "USD", "Best quality, lowest price", simpleColors, rubberFactory));

        //star wars
        productDao.add(new Product("DuckMaul", 5.90f, "USD", "Darth Maul rubber duck", starWars, quackQuack));
        productDao.add(new Product("PrincessDuck", 5.90f, "USD", "Princess Lila rubber duck", starWars, quackQuack));
        productDao.add(new Product("Chewquackka", 5.90f, "USD", "Chewbacca rubber duck", starWars, rubberFactory));

        //rock stars
        productDao.add(new Product("ElvisDuck", 5.90f, "USD", "Elvis Presley rubber duck", rockStar, rubberFactory));
        productDao.add(new Product("EltonDuck", 5.90f, "USD", "Elton John rubber duck", rockStar, quackQuack));

        daoManager.addDao(productDao, productCategoryDao, supplierDao, null);
    }
}
