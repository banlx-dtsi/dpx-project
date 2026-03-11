package com.axelor.studio.db;

import com.axelor.apps.base.db.Unit;
import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import org.hibernate.annotations.Type;

@Entity
@Cacheable
@Table(
  name = "STUDIO_APP_PURCHASE",
  indexes = @Index(
    columnList = "purchase_unit"
  )
)
@Track(
  fields = {
    @TrackField(
      name = "managePurchaseOrderVersion",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "managePurchasesUnits",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "manageMultiplePurchaseQuantity",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "isEnabledProductDescriptionCopy",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "manageSupplierCatalog",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "isDisplayPurchaseOrderLineNumber",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "enablePurchasesProductByTradName",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "purchaseUnit",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "manageCallForTender",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "allowValidatedOrderModification",
      on = TrackEvent.UPDATE
    )
  }
)
public class AppPurchase extends AuditableModel {

  @Id
  @EntitySequence(
    name = "STUDIO_APP_PURCHASE_SEQ"
  )
  private Long id;

  @EqualsInclude
  @JoinColumn(
    unique = true
  )
  @OneToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private App app;

  @Widget(
    title = "Manage purchase order versions",
    help = "This option enables the ability to create multiple versions of a supplier quotation, allowing modification of a quotation to create a new version and track its history."
  )
  private Boolean managePurchaseOrderVersion = Boolean.FALSE;

  @Widget(
    title = "Manage purchases unit on products",
    help = "This function is useful when the purchase units of a product from a supplier are different from your sales units (for example if you buy a product by the ton and resell it by the kilo). When creating supplier orders, the selected product will be directly shown with its purchase unit, avoiding manual conversions."
  )
  private Boolean managePurchasesUnits = Boolean.FALSE;

  @Widget(
    title = "Manage multiple purchase quantity",
    help = "This option allows defining multiple purchase quantities on a product. You can specify that a product can only be purchased in certain multiples (for example quantities of 5 and 10 if the multiple is 5). If the quantity selected on an order line differs, the line cannot be validated and a message will indicate the allowed quantities."
  )
  private Boolean manageMultiplePurchaseQuantity = Boolean.FALSE;

  @Widget(
    title = "Enable product description copy",
    help = "Displays the product description (description field from the product form) on purchase order lines."
  )
  private Boolean isEnabledProductDescriptionCopy = Boolean.FALSE;

  @Widget(
    title = "Manage supplier catalog",
    help = "Allows managing a product catalog for each supplier. It will be possible to link products to suppliers and manage a supplier product catalog."
  )
  private Boolean manageSupplierCatalog = Boolean.FALSE;

  @Widget(
    title = "Display purchase order line number",
    help = "Assigns a number to purchase order lines."
  )
  private Boolean isDisplayPurchaseOrderLineNumber = Boolean.FALSE;

  @Widget(
    title = "Filter products by trading name",
    help = "If this option is enabled, a panel allowing to purchase this product by trading name appears on product forms. This allows defining which products trading names can purchase, and filtering only purchasable products by trading name on quotations."
  )
  private Boolean enablePurchasesProductByTradName = Boolean.FALSE;

  @Widget(
    title = "Default unit",
    help = "This is the default purchase unit for a product when it is created."
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Unit purchaseUnit;

  @Widget(
    title = "Manage call for tender"
  )
  private Boolean manageCallForTender = Boolean.FALSE;

  @Widget(
    title = "Allow validated order modification"
  )
  private Boolean allowValidatedOrderModification = Boolean.FALSE;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public AppPurchase() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public App getApp() {
    return app;
  }

  public void setApp(App app) {
    this.app = app;
  }

  /**
   * This option enables the ability to create multiple versions of a supplier quotation, allowing modification of a quotation to create a new version and track its history.
   *
   * @return the property value
   */
  public Boolean getManagePurchaseOrderVersion() {
    return managePurchaseOrderVersion == null ? Boolean.FALSE : managePurchaseOrderVersion;
  }

  public void setManagePurchaseOrderVersion(Boolean managePurchaseOrderVersion) {
    this.managePurchaseOrderVersion = managePurchaseOrderVersion;
  }

  /**
   * This function is useful when the purchase units of a product from a supplier are different from your sales units (for example if you buy a product by the ton and resell it by the kilo). When creating supplier orders, the selected product will be directly shown with its purchase unit, avoiding manual conversions.
   *
   * @return the property value
   */
  public Boolean getManagePurchasesUnits() {
    return managePurchasesUnits == null ? Boolean.FALSE : managePurchasesUnits;
  }

  public void setManagePurchasesUnits(Boolean managePurchasesUnits) {
    this.managePurchasesUnits = managePurchasesUnits;
  }

  /**
   * This option allows defining multiple purchase quantities on a product. You can specify that a product can only be purchased in certain multiples (for example quantities of 5 and 10 if the multiple is 5). If the quantity selected on an order line differs, the line cannot be validated and a message will indicate the allowed quantities.
   *
   * @return the property value
   */
  public Boolean getManageMultiplePurchaseQuantity() {
    return manageMultiplePurchaseQuantity == null ? Boolean.FALSE : manageMultiplePurchaseQuantity;
  }

  public void setManageMultiplePurchaseQuantity(Boolean manageMultiplePurchaseQuantity) {
    this.manageMultiplePurchaseQuantity = manageMultiplePurchaseQuantity;
  }

  /**
   * Displays the product description (description field from the product form) on purchase order lines.
   *
   * @return the property value
   */
  public Boolean getIsEnabledProductDescriptionCopy() {
    return isEnabledProductDescriptionCopy == null ? Boolean.FALSE : isEnabledProductDescriptionCopy;
  }

  public void setIsEnabledProductDescriptionCopy(Boolean isEnabledProductDescriptionCopy) {
    this.isEnabledProductDescriptionCopy = isEnabledProductDescriptionCopy;
  }

  /**
   * Allows managing a product catalog for each supplier. It will be possible to link products to suppliers and manage a supplier product catalog.
   *
   * @return the property value
   */
  public Boolean getManageSupplierCatalog() {
    return manageSupplierCatalog == null ? Boolean.FALSE : manageSupplierCatalog;
  }

  public void setManageSupplierCatalog(Boolean manageSupplierCatalog) {
    this.manageSupplierCatalog = manageSupplierCatalog;
  }

  /**
   * Assigns a number to purchase order lines.
   *
   * @return the property value
   */
  public Boolean getIsDisplayPurchaseOrderLineNumber() {
    return isDisplayPurchaseOrderLineNumber == null ? Boolean.FALSE : isDisplayPurchaseOrderLineNumber;
  }

  public void setIsDisplayPurchaseOrderLineNumber(Boolean isDisplayPurchaseOrderLineNumber) {
    this.isDisplayPurchaseOrderLineNumber = isDisplayPurchaseOrderLineNumber;
  }

  /**
   * If this option is enabled, a panel allowing to purchase this product by trading name appears on product forms. This allows defining which products trading names can purchase, and filtering only purchasable products by trading name on quotations.
   *
   * @return the property value
   */
  public Boolean getEnablePurchasesProductByTradName() {
    return enablePurchasesProductByTradName == null ? Boolean.FALSE : enablePurchasesProductByTradName;
  }

  public void setEnablePurchasesProductByTradName(Boolean enablePurchasesProductByTradName) {
    this.enablePurchasesProductByTradName = enablePurchasesProductByTradName;
  }

  /**
   * This is the default purchase unit for a product when it is created.
   *
   * @return the property value
   */
  public Unit getPurchaseUnit() {
    return purchaseUnit;
  }

  public void setPurchaseUnit(Unit purchaseUnit) {
    this.purchaseUnit = purchaseUnit;
  }

  public Boolean getManageCallForTender() {
    return manageCallForTender == null ? Boolean.FALSE : manageCallForTender;
  }

  public void setManageCallForTender(Boolean manageCallForTender) {
    this.manageCallForTender = manageCallForTender;
  }

  public Boolean getAllowValidatedOrderModification() {
    return allowValidatedOrderModification == null ? Boolean.FALSE : allowValidatedOrderModification;
  }

  public void setAllowValidatedOrderModification(Boolean allowValidatedOrderModification) {
    this.allowValidatedOrderModification = allowValidatedOrderModification;
  }

  public String getAttrs() {
    return attrs;
  }

  public void setAttrs(String attrs) {
    this.attrs = attrs;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof AppPurchase)) return false;

    final AppPurchase other = (AppPurchase) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getApp(), other.getApp())
      && (getApp() != null);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .add("managePurchaseOrderVersion", getManagePurchaseOrderVersion())
      .add("managePurchasesUnits", getManagePurchasesUnits())
      .add("manageMultiplePurchaseQuantity", getManageMultiplePurchaseQuantity())
      .add("isEnabledProductDescriptionCopy", getIsEnabledProductDescriptionCopy())
      .add("manageSupplierCatalog", getManageSupplierCatalog())
      .add("isDisplayPurchaseOrderLineNumber", getIsDisplayPurchaseOrderLineNumber())
      .add("enablePurchasesProductByTradName", getEnablePurchasesProductByTradName())
      .add("manageCallForTender", getManageCallForTender())
      .add("allowValidatedOrderModification", getAllowValidatedOrderModification())
      .omitNullValues()
      .toString();
  }
}
