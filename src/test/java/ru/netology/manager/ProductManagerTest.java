package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;


public class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Product book1 = new Book(1, "История", 1500, "Борис Акунин");
    private Product book2 = new Book(4, "Тайна", 405, "Xiaomi");
    private Product book3 = new Book(3, "Статский советник", 595, "Борис Акунин");
    private Product phone1 = new Smartphone(2, "Iphone", 10000, "Apple");
    private Product phone2 = new Smartphone(5, "Redmi Note", 5980, "Xiaomi");

    @BeforeEach
    public void manageAdd() {
        manager.add(book1);
        manager.add(phone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone2);
    }

    @Test
    public void shouldSearchByBook() { ///Искать по книге
        Product[] actual = manager.searchBy("Борис Акунин");
        Product[] expected = new Product[]{book1, book3};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByPhone() { ///Искать по телефону
        Product[] actual = manager.searchBy("Iphone");
        Product[] expected = new Product[]{phone1};

        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldFindExistingBookByName() {
        String nameBook = "Статский советник";
        Product[] expected = new Product[]{book3};
        Product[] actual = manager.searchBy(nameBook);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindExistingPhoneByName() {
        String namePhone = "Redmi Note";
        Product[] expected = new Product[]{phone2};
        Product[] actual = manager.searchBy(namePhone);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindExistingPhoneByCreator() {
        String creatorPhone = "Iphone";
        Product[] expected = new Product[]{phone1};
        Product[] actual = manager.searchBy(creatorPhone);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenMissingProduct() {
        Product[] actual = manager.searchBy("нет такого продукта");
        Product[] expected = new Product[]{};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByPhoneAndBook() {
        Product[] actual = manager.searchBy("Xiaomi");
        Product[] expected = new Product[]{book2, phone2};

    }
    @Test
    public void shouldRemoveByIdNotExist() {

        assertThrows(NotFoundException.class, () -> repository.removeById(6));
    }
}
