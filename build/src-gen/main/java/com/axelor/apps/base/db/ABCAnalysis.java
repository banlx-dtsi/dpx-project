package com.axelor.apps.base.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.db.hibernate.type.JsonType;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.hibernate.Length;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "BASE_ABC_ANALYSIS",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "abcAnalysisSeq"
    ),
    @Index(
      columnList = "company"
    )
  }
)
public class ABCAnalysis extends AuditableModel {

  @Id
  @EntitySequence(
    name = "BASE_ABC_ANALYSIS_SEQ"
  )
  private Long id;

  @Widget(
    title = "Name"
  )
  private String name;

  @Widget(
    title = "ABC Analysis N°",
    readonly = true
  )
  @NameColumn
  private String abcAnalysisSeq;

  @Widget(
    title = "Description"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Column(
    length = Length.LONG32
  )
  private String description;

  @Widget(
    title = "Type",
    selection = "abc.analysis.type.select"
  )
  private String typeSelect = "com.axelor.apps.base.service.ABCAnalysisServiceImpl";

  @Widget(
    title = "Status",
    selection = "abc.analysis.status.select"
  )
  private Integer statusSelect = 1;

  @Widget(
    title = "Company"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Company company;

  @Widget(
    title = "ABC Classes"
  )
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "abcAnalysis",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<ABCAnalysisClass> abcAnalysisClassList;

  @Widget(
    title = "Products"
  )
  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<Product> productSet;

  @Widget(
    title = "Product categories"
  )
  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<ProductCategory> productCategorySet;

  @Widget(
    title = "Product families"
  )
  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<ProductFamily> productFamilySet;

  @Widget(
    title = "Start date"
  )
  private LocalDate startDate;

  @Widget(
    title = "End date"
  )
  private LocalDate endDate;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(JsonType.class)
  private String attrs;

  public ABCAnalysis() {
  }

  public ABCAnalysis(String name) {
    this.name = name;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbcAnalysisSeq() {
    return abcAnalysisSeq;
  }

  public void setAbcAnalysisSeq(String abcAnalysisSeq) {
    this.abcAnalysisSeq = abcAnalysisSeq;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTypeSelect() {
    return typeSelect;
  }

  public void setTypeSelect(String typeSelect) {
    this.typeSelect = typeSelect;
  }

  public Integer getStatusSelect() {
    return statusSelect == null ? 0 : statusSelect;
  }

  public void setStatusSelect(Integer statusSelect) {
    this.statusSelect = statusSelect;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public List<ABCAnalysisClass> getAbcAnalysisClassList() {
    return abcAnalysisClassList;
  }

  public void setAbcAnalysisClassList(List<ABCAnalysisClass> abcAnalysisClassList) {
    this.abcAnalysisClassList = abcAnalysisClassList;
  }

  /**
   * Add the given {@link ABCAnalysisClass} item to the {@code abcAnalysisClassList} collection.
   *
   * <p>
   * It sets {@code item.abcAnalysis = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addAbcAnalysisClassListItem(ABCAnalysisClass item) {
    if (getAbcAnalysisClassList() == null) {
      setAbcAnalysisClassList(new ArrayList<>());
    }
    getAbcAnalysisClassList().add(item);
    item.setAbcAnalysis(this);
  }

  /**
   * Remove the given {@link ABCAnalysisClass} item from the {@code abcAnalysisClassList} collection.
   *
   * @param item the item to remove
   */
  public void removeAbcAnalysisClassListItem(ABCAnalysisClass item) {
    if (getAbcAnalysisClassList() == null) {
      return;
    }
    getAbcAnalysisClassList().remove(item);
  }

  /**
   * Clear the {@code abcAnalysisClassList} collection.
   */
  public void clearAbcAnalysisClassList() {
    if (getAbcAnalysisClassList() != null) {
      getAbcAnalysisClassList().clear();
    }
  }

  public Set<Product> getProductSet() {
    return productSet;
  }

  public void setProductSet(Set<Product> productSet) {
    this.productSet = productSet;
  }

  /**
   * Add the given {@link Product} item to the {@code productSet} collection.
   *
   * @param item the item to add
   */
  public void addProductSetItem(Product item) {
    if (getProductSet() == null) {
      setProductSet(new HashSet<>());
    }
    getProductSet().add(item);
  }

  /**
   * Remove the given {@link Product} item from the {@code productSet} collection.
   *
   * @param item the item to remove
   */
  public void removeProductSetItem(Product item) {
    if (getProductSet() == null) {
      return;
    }
    getProductSet().remove(item);
  }

  /**
   * Clear the {@code productSet} collection.
   */
  public void clearProductSet() {
    if (getProductSet() != null) {
      getProductSet().clear();
    }
  }

  public Set<ProductCategory> getProductCategorySet() {
    return productCategorySet;
  }

  public void setProductCategorySet(Set<ProductCategory> productCategorySet) {
    this.productCategorySet = productCategorySet;
  }

  /**
   * Add the given {@link ProductCategory} item to the {@code productCategorySet} collection.
   *
   * @param item the item to add
   */
  public void addProductCategorySetItem(ProductCategory item) {
    if (getProductCategorySet() == null) {
      setProductCategorySet(new HashSet<>());
    }
    getProductCategorySet().add(item);
  }

  /**
   * Remove the given {@link ProductCategory} item from the {@code productCategorySet} collection.
   *
   * @param item the item to remove
   */
  public void removeProductCategorySetItem(ProductCategory item) {
    if (getProductCategorySet() == null) {
      return;
    }
    getProductCategorySet().remove(item);
  }

  /**
   * Clear the {@code productCategorySet} collection.
   */
  public void clearProductCategorySet() {
    if (getProductCategorySet() != null) {
      getProductCategorySet().clear();
    }
  }

  public Set<ProductFamily> getProductFamilySet() {
    return productFamilySet;
  }

  public void setProductFamilySet(Set<ProductFamily> productFamilySet) {
    this.productFamilySet = productFamilySet;
  }

  /**
   * Add the given {@link ProductFamily} item to the {@code productFamilySet} collection.
   *
   * @param item the item to add
   */
  public void addProductFamilySetItem(ProductFamily item) {
    if (getProductFamilySet() == null) {
      setProductFamilySet(new HashSet<>());
    }
    getProductFamilySet().add(item);
  }

  /**
   * Remove the given {@link ProductFamily} item from the {@code productFamilySet} collection.
   *
   * @param item the item to remove
   */
  public void removeProductFamilySetItem(ProductFamily item) {
    if (getProductFamilySet() == null) {
      return;
    }
    getProductFamilySet().remove(item);
  }

  /**
   * Clear the {@code productFamilySet} collection.
   */
  public void clearProductFamilySet() {
    if (getProductFamilySet() != null) {
      getProductFamilySet().clear();
    }
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
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
    if (!(obj instanceof ABCAnalysis)) return false;

    final ABCAnalysis other = (ABCAnalysis) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .add("name", getName())
      .add("abcAnalysisSeq", getAbcAnalysisSeq())
      .add("typeSelect", getTypeSelect())
      .add("statusSelect", getStatusSelect())
      .add("startDate", getStartDate())
      .add("endDate", getEndDate())
      .omitNullValues()
      .toString();
  }
}
