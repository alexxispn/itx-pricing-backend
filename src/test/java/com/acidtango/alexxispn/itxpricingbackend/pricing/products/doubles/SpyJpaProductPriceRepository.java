package com.acidtango.alexxispn.itxpricingbackend.pricing.products.doubles;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.JpaProductPriceRepository;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.entities.ProductPriceEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SpyJpaProductPriceRepository implements JpaProductPriceRepository {

    private String receivedProductCode;
    private String receivedBrandCode;
    private Instant receivedStartDate;
    private Instant receivedEndDate;

    @Override
    public List<ProductPriceEntity> findByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String productCode, String brandCode, Instant startDate, Instant endDate) {
        this.receivedProductCode = productCode;
        this.receivedBrandCode = brandCode;
        this.receivedStartDate = startDate;
        this.receivedEndDate = endDate;

        return Collections.emptyList();
    }

    public String getReceivedProductCode() {
        return receivedProductCode;
    }

    public String getReceivedBrandCode() {
        return receivedBrandCode;
    }

    public Instant getReceivedStartDate() {
        return receivedStartDate;
    }

    public Instant getReceivedEndDate() {
        return receivedEndDate;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ProductPriceEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ProductPriceEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<ProductPriceEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ProductPriceEntity getOne(String s) {
        return null;
    }

    @Override
    public ProductPriceEntity getById(String s) {
        return null;
    }

    @Override
    public ProductPriceEntity getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends ProductPriceEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ProductPriceEntity> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends ProductPriceEntity> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends ProductPriceEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ProductPriceEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ProductPriceEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ProductPriceEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends ProductPriceEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ProductPriceEntity> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<ProductPriceEntity> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<ProductPriceEntity> findAll() {
        return List.of();
    }

    @Override
    public List<ProductPriceEntity> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(ProductPriceEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends ProductPriceEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ProductPriceEntity> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<ProductPriceEntity> findAll(Pageable pageable) {
        return null;
    }
}
