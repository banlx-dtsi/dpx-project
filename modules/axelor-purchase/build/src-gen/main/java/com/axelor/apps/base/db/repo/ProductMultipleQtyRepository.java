package com.axelor.apps.base.db.repo;

import com.axelor.apps.base.db.ProductMultipleQty;
import com.axelor.db.JpaRepository;
import com.axelor.db.Query;

public class ProductMultipleQtyRepository extends JpaRepository<ProductMultipleQty> {

  public ProductMultipleQtyRepository() {
    super(ProductMultipleQty.class);
  }

  public ProductMultipleQty findByName(String name) {
    return Query.of(ProductMultipleQty.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
