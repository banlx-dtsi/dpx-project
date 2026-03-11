package com.axelor.meta.db;

import com.axelor.apps.base.db.MapView;
import com.axelor.auth.db.AuditableModel;
import com.axelor.auth.db.Group;
import com.axelor.auth.db.Role;
import com.axelor.auth.db.User;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.axelor.db.hibernate.sequence.EntitySequence;
import com.google.common.base.MoreObjects;
import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This object stores the menus.
 */
@Entity
@Cacheable
@Table(
  name = "META_MENU",
  indexes = {
    @Index(
      columnList = "name"
    ),
    @Index(
      columnList = "title"
    ),
    @Index(
      columnList = "parent"
    ),
    @Index(
      columnList = "action"
    ),
    @Index(
      columnList = "user_id"
    )
  }
)
public class MetaMenu extends AuditableModel {

  @Id
  @EntitySequence(
    name = "META_MENU_SEQ"
  )
  private Long id;

  private Integer priority = 0;

  @Widget(
    title = "aop.meta.order"
  )
  @Column(
    name = "order_seq"
  )
  private Integer order = 0;

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

  @NameColumn
  @NotNull
  private String title;

  private String icon;

  private String iconBackground;

  private String module;

  @Widget(
    title = "Tag label"
  )
  private String tag;

  @Widget(
    title = "Tag method"
  )
  private String tagGet;

  @Widget(
    title = "Tag count"
  )
  private Boolean tagCount = Boolean.FALSE;

  @Widget(
    title = "Tag style",
    selection = "label.style.selection"
  )
  private String tagStyle;

  @Widget(
    title = "Left menu"
  )
  @Column(
    name = "left_menu"
  )
  private Boolean left = Boolean.TRUE;

  @Widget(
    title = "Mobile menu"
  )
  @Column(
    name = "mobile_menu"
  )
  private Boolean mobile = Boolean.FALSE;

  @Widget(
    title = "Hidden menu"
  )
  private Boolean hidden = Boolean.FALSE;

  private String link;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaMenu parent;

  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MetaAction action;

  @JoinColumn(
    name = "user_id"
  )
  @ManyToOne(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private User user;

  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  @JoinTable(
    name = "meta_menu_groups",
    joinColumns = @JoinColumn(
      name = "meta_menu_id"
    ),
    inverseJoinColumns = @JoinColumn(
      name = "group_id"
    )
  )
  private Set<Group> groups;

  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<Role> roles;

  @Widget(
    title = "Condition to check",
    help = "Only use this menu-item if the given expression is true (ie `if` condition)."
  )
  @Size(
    max = 1024
  )
  private String conditionToCheck;

  @Widget(
    title = "Module to check",
    help = "Only use this menu-item if the given module is installed (ie `if-module` condition)."
  )
  private String moduleToCheck;

  @Widget(
    title = "Map view"
  )
  @OneToOne(
    fetch = FetchType.LAZY,
    mappedBy = "metaMenu",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private MapView mapView;

  public MetaMenu() {
  }

  public MetaMenu(String name) {
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

  public Integer getPriority() {
    return priority == null ? 0 : priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Integer getOrder() {
    return order == null ? 0 : order;
  }

  public void setOrder(Integer order) {
    this.order = order;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getIconBackground() {
    return iconBackground;
  }

  public void setIconBackground(String iconBackground) {
    this.iconBackground = iconBackground;
  }

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getTagGet() {
    return tagGet;
  }

  public void setTagGet(String tagGet) {
    this.tagGet = tagGet;
  }

  public Boolean getTagCount() {
    return tagCount == null ? Boolean.FALSE : tagCount;
  }

  public void setTagCount(Boolean tagCount) {
    this.tagCount = tagCount;
  }

  public String getTagStyle() {
    return tagStyle;
  }

  public void setTagStyle(String tagStyle) {
    this.tagStyle = tagStyle;
  }

  public Boolean getLeft() {
    return left == null ? Boolean.FALSE : left;
  }

  public void setLeft(Boolean left) {
    this.left = left;
  }

  public Boolean getMobile() {
    return mobile == null ? Boolean.FALSE : mobile;
  }

  public void setMobile(Boolean mobile) {
    this.mobile = mobile;
  }

  public Boolean getHidden() {
    return hidden == null ? Boolean.FALSE : hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public MetaMenu getParent() {
    return parent;
  }

  public void setParent(MetaMenu parent) {
    this.parent = parent;
  }

  public MetaAction getAction() {
    return action;
  }

  public void setAction(MetaAction action) {
    this.action = action;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<Group> getGroups() {
    return groups;
  }

  public void setGroups(Set<Group> groups) {
    this.groups = groups;
  }

  /**
   * Add the given {@link Group} item to the {@code groups} collection.
   *
   * @param item the item to add
   */
  public void addGroup(Group item) {
    if (getGroups() == null) {
      setGroups(new HashSet<>());
    }
    getGroups().add(item);
  }

  /**
   * Remove the given {@link Group} item from the {@code groups} collection.
   *
   * @param item the item to remove
   */
  public void removeGroup(Group item) {
    if (getGroups() == null) {
      return;
    }
    getGroups().remove(item);
  }

  /**
   * Clear the {@code groups} collection.
   */
  public void clearGroups() {
    if (getGroups() != null) {
      getGroups().clear();
    }
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

  /**
   * Only use this menu-item if the given expression is true (ie `if` condition).
   *
   * @return the property value
   */
  public String getConditionToCheck() {
    return conditionToCheck;
  }

  public void setConditionToCheck(String conditionToCheck) {
    this.conditionToCheck = conditionToCheck;
  }

  /**
   * Only use this menu-item if the given module is installed (ie `if-module` condition).
   *
   * @return the property value
   */
  public String getModuleToCheck() {
    return moduleToCheck;
  }

  public void setModuleToCheck(String moduleToCheck) {
    this.moduleToCheck = moduleToCheck;
  }

  public MapView getMapView() {
    return mapView;
  }

  public void setMapView(MapView mapView) {
    if (getMapView() != null) {
      getMapView().setMetaMenu(null);
    }
    if (mapView != null) {
      mapView.setMetaMenu(this);
    }
    this.mapView = mapView;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof MetaMenu)) return false;

    final MetaMenu other = (MetaMenu) obj;
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
      .add("priority", getPriority())
      .add("order", getOrder())
      .add("xmlId", getXmlId())
      .add("name", getName())
      .add("title", getTitle())
      .add("icon", getIcon())
      .add("iconBackground", getIconBackground())
      .add("module", getModule())
      .add("tag", getTag())
      .add("tagGet", getTagGet())
      .omitNullValues()
      .toString();
  }
}
