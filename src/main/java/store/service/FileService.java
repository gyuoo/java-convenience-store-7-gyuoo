package store.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import store.domain.product.Product;
import store.domain.product.ProductRepository;
import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionRepository;
import store.util.FileParser;

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

    public void savePromotions(List<Promotion> promotions) {
        for (Promotion promotion : promotions) {
            promotionRepository.save(promotion);
        }
    }

    private List<Promotion> readPromotions(String filePath) throws IOException {
        List<Promotion> promotions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            skipHeader(br);
            String line;
            while ((line = br.readLine()) != null) {
                Promotion promotion = fileParser.parsePromotion(parseLine(line));
                promotions.add(promotion);
            }
        }
        return promotions;
    }

    public void saveProducts(List<Product> products) {
        for (Product product : products) {
            productRepository.save(product);
        }
    }

    private List<Product> readProducts(String filePath) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            skipHeader(br);
            String line;
            while ((line = br.readLine()) != null) {
                Product product = fileParser.parseProduct(parseLine(line));
                products.add(product);
            }
        }
        return products;
    }

    private void skipHeader(BufferedReader br) throws IOException {
        br.readLine(); // Skip header line
    }

    private List<String> parseLine(String line) {
        return Arrays.stream(line.split(",")).toList();
    }
}
