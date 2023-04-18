package kr.ac.hansung;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
//        Configuration conf = new Configuration();
//        conf.configure();
//        SessionFactory sessionFactory = conf.buildSessionFactory();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Product product1 = new Product();
        product1.setName("노트북1");
        product1.setPrice(2000);
        product1.setDescription("Awesome Notebook");

        Product product2 = new Product();
        product2.setName("노트북2");
        product2.setPrice(1000);
        product2.setDescription("Powerful Notebook");
        // save를 할 때 id가 할당된다.
        session.save(product1);
        session.save(product2);

//        Product savedProduct = session.get(Product.class, product1.getId());
//        System.out.println("saved product: " + savedProduct);

        // HQL
        Query<Product> aQuery = session.createQuery("from Product order by name ", Product.class);
        List<Product> products = aQuery.getResultList();
        System.out.println(products);

        tx.commit();    // 이 때, instance를 DB에 저장
        session.close();
        sessionFactory.close();
    }
}

// hibernate에서는 조회한 다음에 save를 수행한다.
// Entity Manager는 Session을 말한다.
// Persistence Context에 저장을 해두고, 나중에 transaction commit이나 session close를 할 때
// DB에 저장된다.