package store.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import store.domain.product.Product;
import store.domain.product.ProductRepository;
import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionRepository;
import store.global.util.FileParser;

public class FileService {

    private final FileParser fileParser;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;

    public FileService(FileParser fileParser, ProductRepository productRepository,
        PromotionRepository promotionRepository) {
        this.fileParser = fileParser;
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getPromotions() throws IOException {
        List<Promotion> promotions = loadPromotionsFromFile(
            "src/main/resources/promotions.md");
        savePromotions(promotions);
        return promotions;
    }

    public List<Product> getProducts() throws IOException {
        List<Product> products = loadProductsFromFile("src/main/resources/products.md");
        saveProducts(products);
        return products;
    }

    private void savePromotions(List<Promotion> promotions) {
        promotions.forEach(promotionRepository::save);
    }

    private void saveProducts(List<Product> products) {
        products.forEach(productRepository::save);
    }

    private List<Promotion> loadPromotionsFromFile(String filePath) throws IOException {
        return loadEntitiesFromFile(filePath, line -> fileParser.parsePromotion(parseLine(line)));
    }

    private List<Product> loadProductsFromFile(String filePath) throws IOException {
        return loadEntitiesFromFile(filePath, line -> fileParser.parseProduct(parseLine(line)));
    }

    private <T> List<T> loadEntitiesFromFile(String filePath, Function<String, T> parseFunction)
        throws IOException {
        List<T> entities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            skipHeader(br);
            String line;
            while ((line = br.readLine()) != null) {
                entities.add(parseFunction.apply(line));
            }
        }
        return entities;
    }

    private void skipHeader(BufferedReader br) throws IOException {
        br.readLine(); // Skip header line
    }

    private List<String> parseLine(String line) {
        return Arrays.stream(line.split(",")).toList();
    }
}
