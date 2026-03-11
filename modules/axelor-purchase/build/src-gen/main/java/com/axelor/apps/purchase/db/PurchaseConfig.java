package com.axelor.apps.purchase.db;

import com.axelor.apps.base.db.Company;
import com.axelor.apps.base.db.PrintingTemplate;
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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import org.hibernate.Length;
import org.hibernate.annotations.Type;

@Entity
@Cacheable
@Table(
  name = "PURCHASE_PURCHASE_CONFIG",
  indexes = @Index(
    columnList = "purchase_order_print_template"
  )
)
@Track(
  fields = {
    @TrackField(
      name = "company",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "purchaseOrderInAtiSelect",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "purchaseOrderSupplierBox",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "displayBuyerOnPrinting",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "displayProductCodeOnPrinting",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "displayTaxDetailOnPrinting",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "displaySupplierCodeOnPrinting",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "displayProductDetailOnPrinting",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "priceRequest",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "isAnalyticDistributionRequired",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "displayPriceOnQuotationRequest",
      on = TrackEvent.UPDATE
    ),
    @TrackField(
      name = "purchaseOrderPrintTemplate",
      on = TrackEvent.UPDATE
    )
  }
)
public class PurchaseConfig extends AuditableModel {

  @Id
  @EntitySequence(
    name = "PURCHASE_PURCHASE_CONFIG_SEQ"
  )
  private Long id;

  @EqualsInclude
  @Widget(
    title = "Company",
    help = "Company for which the configurations are made."
  )
  @NotNull
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
  private Company company;

  @Widget(
    title = "Purchase orders ATI/WT",
    help = "It is possible to choose whether quotations/orders are always established excluding tax, always including tax, by default excluding tax, or by default including tax. The by default excluding tax and by default including tax options allow case-by-case modification (via a checkbox).",
    selection = "base.in.ati.select"
  )
  private Integer purchaseOrderInAtiSelect = 1;

  @Widget(
    title = "Supplier box in purchase order",
    help = "This field allows you to customize a box with information of your choice on quotation/order printings using an HTML field.",
    multiline = true
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String purchaseOrderSupplierBox;

  @Widget(
    title = "Display price on requested purchase printing",
    help = "This field allows displaying the purchase price recorded on the product form (supplier catalog price) on a quotation request (supplier quotation printing)."
  )
  private Boolean displayPriceOnQuotationRequest = Boolean.FALSE;

  @Widget(
    title = "Display buyer on printing",
    help = "On quotation/order printing, displays the buyer name."
  )
  private Boolean displayBuyerOnPrinting = Boolean.FALSE;

  @Widget(
    title = "Display product code on printing",
    help = "On quotation/order printing, displays the product code."
  )
  private Boolean displayProductCodeOnPrinting = Boolean.FALSE;

  @Widget(
    title = "Display tax detail on printing",
    help = "On quotation/order printing, displays the tax details (name, rate, and amount)."
  )
  private Boolean displayTaxDetailOnPrinting = Boolean.FALSE;

  @Widget(
    title = "Display supplier code on printing",
    help = "On quotation/order printing, displays the supplier code."
  )
  private Boolean displaySupplierCodeOnPrinting = Boolean.FALSE;

  @Widget(
    title = "Display product detail on printing",
    help = "On quotation/order printing, displays the product description from the product form."
  )
  private Boolean displayProductDetailOnPrinting = Boolean.FALSE;

  @Widget(
    title = "Message for requesting prices",
    help = "Allows customizing a default message on quotation requests, which will appear on the printing."
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String priceRequest;

  @Widget(
    title = "Analytic distribution required on purchase order line",
    help = "If this option is enabled, it is mandatory that on each order line (Analytics tab), the Analytic distribution template field is filled in."
  )
  private Boolean isAnalyticDistributionRequired = Boolean.FALSE;

  @Widget(
    title = "Purchase order template",
    help = "You can define here the default printing template for the company purchase quotations/orders."
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private PrintingTemplate purchaseOrderPrintTemplate;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public PurchaseConfig() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Company for which the configurations are made.
   *
   * @return the property value
   */
  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  /**
   * It is possible to choose whether quotations/orders are always established excluding tax, always including tax, by default excluding tax, or by default including tax. The by default excluding tax and by default including tax options allow case-by-case modification (via a checkbox).
   *
   * @return the property value
   */
  public Integer getPurchaseOrderInAtiSelect() {
    return purchaseOrderInAtiSelect == null ? 0 : purchaseOrderInAtiSelect;
  }

  public void setPurchaseOrderInAtiSelect(Integer purchaseOrderInAtiSelect) {
    this.purchaseOrderInAtiSelect = purchaseOrderInAtiSelect;
  }

  /**
   * This field allows you to customize a box with information of your choice on quotation/order printings using an HTML field.
   *
   * @return the property value
   */
  public String getPurchaseOrderSupplierBox() {
    return purchaseOrderSupplierBox;
  }

  public void setPurchaseOrderSupplierBox(String purchaseOrderSupplierBox) {
    this.purchaseOrderSupplierBox = purchaseOrderSupplierBox;
  }

  /**
   * This field allows displaying the purchase price recorded on the product form (supplier catalog price) on a quotation request (supplier quotation printing).
   *
   * @return the property value
   */
  public Boolean getDisplayPriceOnQuotationRequest() {
    return displayPriceOnQuotationRequest == null ? Boolean.FALSE : displayPriceOnQuotationRequest;
  }

  public void setDisplayPriceOnQuotationRequest(Boolean displayPriceOnQuotationRequest) {
    this.displayPriceOnQuotationRequest = displayPriceOnQuotationRequest;
  }

  /**
   * On quotation/order printing, displays the buyer name.
   *
   * @return the property value
   */
  public Boolean getDisplayBuyerOnPrinting() {
    return displayBuyerOnPrinting == null ? Boolean.FALSE : displayBuyerOnPrinting;
  }

  public void setDisplayBuyerOnPrinting(Boolean displayBuyerOnPrinting) {
    this.displayBuyerOnPrinting = displayBuyerOnPrinting;
  }

  /**
   * On quotation/order printing, displays the product code.
   *
   * @return the property value
   */
  public Boolean getDisplayProductCodeOnPrinting() {
    return displayProductCodeOnPrinting == null ? Boolean.FALSE : displayProductCodeOnPrinting;
  }

  public void setDisplayProductCodeOnPrinting(Boolean displayProductCodeOnPrinting) {
    this.displayProductCodeOnPrinting = displayProductCodeOnPrinting;
  }

  /**
   * On quotation/order printing, displays the tax details (name, rate, and amount).
   *
   * @return the property value
   */
  public Boolean getDisplayTaxDetailOnPrinting() {
    return displayTaxDetailOnPrinting == null ? Boolean.FALSE : displayTaxDetailOnPrinting;
  }

  public void setDisplayTaxDetailOnPrinting(Boolean displayTaxDetailOnPrinting) {
    this.displayTaxDetailOnPrinting = displayTaxDetailOnPrinting;
  }

  /**
   * On quotation/order printing, displays the supplier code.
   *
   * @return the property value
   */
  public Boolean getDisplaySupplierCodeOnPrinting() {
    return displaySupplierCodeOnPrinting == null ? Boolean.FALSE : displaySupplierCodeOnPrinting;
  }

  public void setDisplaySupplierCodeOnPrinting(Boolean displaySupplierCodeOnPrinting) {
    this.displaySupplierCodeOnPrinting = displaySupplierCodeOnPrinting;
  }

  /**
   * On quotation/order printing, displays the product description from the product form.
   *
   * @return the property value
   */
  public Boolean getDisplayProductDetailOnPrinting() {
    return displayProductDetailOnPrinting == null ? Boolean.FALSE : displayProductDetailOnPrinting;
  }

  public void setDisplayProductDetailOnPrinting(Boolean displayProductDetailOnPrinting) {
    this.displayProductDetailOnPrinting = displayProductDetailOnPrinting;
  }

  /**
   * Allows customizing a default message on quotation requests, which will appear on the printing.
   *
   * @return the property value
   */
  public String getPriceRequest() {
    return priceRequest;
  }

  public void setPriceRequest(String priceRequest) {
    this.priceRequest = priceRequest;
  }

  /**
   * If this option is enabled, it is mandatory that on each order line (Analytics tab), the Analytic distribution template field is filled in.
   *
   * @return the property value
   */
  public Boolean getIsAnalyticDistributionRequired() {
    return isAnalyticDistributionRequired == null ? Boolean.FALSE : isAnalyticDistributionRequired;
  }

  public void setIsAnalyticDistributionRequired(Boolean isAnalyticDistributionRequired) {
    this.isAnalyticDistributionRequired = isAnalyticDistributionRequired;
  }

  /**
   * You can define here the default printing template for the company purchase quotations/orders.
   *
   * @return the property value
   */
  public PrintingTemplate getPurchaseOrderPrintTemplate() {
    return purchaseOrderPrintTemplate;
  }

  public void setPurchaseOrderPrintTemplate(PrintingTemplate purchaseOrderPrintTemplate) {
    this.purchaseOrderPrintTemplate = purchaseOrderPrintTemplate;
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
    if (!(obj instanceof PurchaseConfig)) return false;

    final PurchaseConfig other = (PurchaseConfig) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getCompany(), other.getCompany())
      && (getCompany() != null);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .add("purchaseOrderInAtiSelect", getPurchaseOrderInAtiSelect())
      .add("displayPriceOnQuotationRequest", getDisplayPriceOnQuotationRequest())
      .add("displayBuyerOnPrinting", getDisplayBuyerOnPrinting())
      .add("displayProductCodeOnPrinting", getDisplayProductCodeOnPrinting())
      .add("displayTaxDetailOnPrinting", getDisplayTaxDetailOnPrinting())
      .add("displaySupplierCodeOnPrinting", getDisplaySupplierCodeOnPrinting())
      .add("displayProductDetailOnPrinting", getDisplayProductDetailOnPrinting())
      .add("isAnalyticDistributionRequired", getIsAnalyticDistributionRequired())
      .omitNullValues()
      .toString();
  }
}
