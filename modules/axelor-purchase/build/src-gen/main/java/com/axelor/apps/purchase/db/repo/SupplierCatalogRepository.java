package com.axelor.apps.purchase.db.repo;

import com.axelor.apps.purchase.db.SupplierCatalog;
import com.axelor.db.JpaRepository;

public class SupplierCatalogRepository extends JpaRepository<SupplierCatalog> {

  public SupplierCatalogRepository() {
    super(SupplierCatalog.class);
  }
}
