package com.axelor.meta.db;

import com.axelor.auth.db.Role;
import com.axelor.db.Model;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.google.common.base.MoreObjects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
  name = "META_ATTRS",
  indexes = @Index(
    columnList = "attr_name"
  )
)
public class MetaAttrs extends Model {

  @Id
  @EntitySequence(
    name = "META_ATTRS_SEQ"
  )
  private Long id;

  @NotNull
  @Column(
    name = "model_name"
  )
  private String model;

  @Column(
    name = "view_name"
  )
  private String view;

  @NotNull
  @Column(
    name = "field_name"
  )
  private String field;

  @NotNull
  @Column(
    name = "attr_name"
  )
  private String name;

  @NotNull
  @Column(
    name = "attr_value"
  )
  private String value;

  @Size(
    max = 1024
  )
  @Column(
    name = "condition_value"
  )
  private String condition;

  @Widget(
    title = "aop.meta.order"
  )
  @Column(
    name = "order_seq"
  )
  private Integer order = 0;

  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<Role> roles;

  private String wkfModelId;

  public MetaAttrs() {
  }

  public MetaAttrs(String name) {
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

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getView() {
    return view;
  }

  public void setView(String view) {
    this.view = view;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public Integer getOrder() {
    return order == null ? 0 : order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  /**
   * Add the given {@link Role} item to the {@code roles} collection.
   *
   * @param item the item to add
   */
  public void addRole(Role item) {
    if (getRoles() == null) {
      setRoles(new HashSet<>());
    }
    getRoles().add(item);
  }

  /**
   * Remove the given {@link Role} item from the {@code roles} collection.
   *
   * @param item the item to remove
   */
  public void removeRole(Role item) {
    if (getRoles() == null) {
      return;
    }
    getRoles().remove(item);
  }

  /**
   * Clear the {@code roles} collection.
   */
  public void clearRoles() {
    if (getRoles() != null) {
      getRoles().clear();
    }
  }

  public String getWkfModelId() {
    return wkfModelId;
  }

  public void setWkfModelId(String wkfModelId) {
    this.wkfModelId = wkfModelId;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof MetaAttrs)) return false;

    final MetaAttrs other = (MetaAttrs) obj;
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
      .add("model", getModel())
      .add("view", getView())
      .add("field", getField())
      .add("name", getName())
      .add("value", getValue())
      .add("condition", getCondition())
      .add("order", getOrder())
      .add("wkfModelId", getWkfModelId())
      .omitNullValues()
      .toString();
  }
}
