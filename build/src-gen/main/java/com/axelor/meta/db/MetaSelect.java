package com.axelor.meta.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.axelor.studio.db.StudioApp;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This object stores the selects.
 */
@Entity
@Cacheable
@Table(
  name = "META_SELECT",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "studio_app"
    )
  }
)
public class MetaSelect extends AuditableModel {

  @Id
  @EntitySequence(
    name = "META_SELECT_SEQ"
  )
  private Long id;

  @EqualsInclude
  @Widget(
    title = "Unique ID"
  )
  @Column(
    unique = true
  )
  private String xmlId;

  @NotNull
  private String name;

  @NotNull
  private Integer priority = 20;

  private String module;

  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "select",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<MetaSelectItem> items;

  @Widget(
    title = "App"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private StudioApp studioApp;

  @Widget(
    title = "Custom"
  )
  private Boolean isCustom = Boolean.FALSE;

  public MetaSelect() {
  }

  public MetaSelect(String name) {
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

  public String getXmlId() {
    return xmlId;
  }

  public void setXmlId(String xmlId) {
    this.xmlId = xmlId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPriority() {
    return priority == null ? 0 : priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  public List<MetaSelectItem> getItems() {
    return items;
  }

  public void setItems(List<MetaSelectItem> items) {
    this.items = items;
  }

  /**
   * Add the given {@link MetaSelectItem} item to the {@code items} collection.
   *
   * <p>
   * It sets {@code item.select = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addItem(MetaSelectItem item) {
    if (getItems() == null) {
      setItems(new ArrayList<>());
    }
    getItems().add(item);
    item.setSelect(this);
  }

  /**
   * Remove the given {@link MetaSelectItem} item from the {@code items} collection.
   *
   * @param item the item to remove
   */
  public void removeItem(MetaSelectItem item) {
    if (getItems() == null) {
      return;
    }
    getItems().remove(item);
  }

  /**
   * Clear the {@code items} collection.
   */
  public void clearItems() {
    if (getItems() != null) {
      getItems().clear();
    }
  }

  public StudioApp getStudioApp() {
    return studioApp;
  }

  public void setStudioApp(StudioApp studioApp) {
    this.studioApp = studioApp;
  }

  public Boolean getIsCustom() {
    return isCustom == null ? Boolean.FALSE : isCustom;
  }

  public void setIsCustom(Boolean isCustom) {
    this.isCustom = isCustom;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof MetaSelect)) return false;

    final MetaSelect other = (MetaSelect) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getXmlId(), other.getXmlId())
      && (getXmlId() != null);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .add("xmlId", getXmlId())
      .add("name", getName())
      .add("priority", getPriority())
      .add("module", getModule())
      .add("isCustom", getIsCustom())
      .omitNullValues()
      .toString();
  }
}
