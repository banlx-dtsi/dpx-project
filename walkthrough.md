# Walkthrough - Simplified Purchase Workflow

I have simplified the purchasing process in the `axelor-purchase` module by removing the "Purchase Request" feature and bypassing the "Quotation" (RFQ) stage in the "Purchase Order" flow.

## Changes Made

### 1. Menu & App Configuration
- **Hid Menus**: The "Internal purchase requests" and "Purchase quotations" menus are now hidden.
- **Deactivated App**: The `purchase-request` app module has been deactivated by commenting out its configuration in [purchase-request.yml](file:///home/banlx/axelor/dpx-project/modules/axelor-purchase/src/main/resources/apps/purchase-request.yml).
- **Updated Purchase Order List**: The "Purchase orders" menu now displays both **Draft** and **Validated** orders, so new orders are visible immediately.

### 2. Purchase Order Workflow Simplification
- **Draft Status Label**: In the Purchase Order form, status 1 is now displayed as **Draft** instead of "Quotation".
- **One-Click Validation**: The "Requested" button (which moved orders to the RFQ stage) has been replaced by a **Validate** button. This button validates the order and moves it directly to the **Validated** status.
- **UI Cleanup**: Hidden the redundant "Validate" button that only appeared in the intermediate "Requested" stage.

## Files Modified

- [Menu.xml](file:///home/banlx/axelor/dpx-project/modules/axelor-purchase/src/main/resources/views/Menu.xml): Hidden menus and updated domains.
- [PurchaseOrder.xml](file:///home/banlx/axelor/dpx-project/modules/axelor-purchase/src/main/resources/views/PurchaseOrder.xml): Simplified buttons and status labels.
- [purchase-request.yml](file:///home/banlx/axelor/dpx-project/modules/axelor-purchase/src/main/resources/apps/purchase-request.yml): Deactivated the module.
- [PurchaseOrder.xml](file:///home/banlx/axelor/dpx-project/modules/axelor-purchase/src/main/resources/views/PurchaseOrder.xml): Removed `if-module="axelor-supplychain"` from "Finish" and "Back to validated" buttons to ensure visibility in simplified setups.

## Verification

To verify these changes:
1.  **Restart the application** (running `./gradlew clean build` might be necessary to ensure XML changes are picked up).
2.  Navigate to the **Purchases** module.
3.  Confirm that **Internal purchase requests** and **Purchase quotations** are no longer in the menu.
4.  Open **Purchase orders** and click **New**.
5.  Observe that the title shows **Draft**.
6.  Add items and click **Validate**. The order status should change directly to **Validated**.

## Lessons Learned (Bài học kinh nghiệm)

- **Đồng bộ Logic Java và XML**: Khi bỏ qua một trạng thái (như "Requested"), cần cập nhật cả logic Java (`WorkflowService`) vì hệ thống thường có các kiểm tra mã cứng (hardcoded) về trạng thái hợp lệ.
- **Tên kỹ thuật của Module**: Trong các file `apps/*.yml`, thuộc tính `dependsOn` nên sử dụng tên kỹ thuật đầy đủ (ví dụ: `axelor-base` thay vì `base`) để đảm bảo các ràng buộc hiển thị đúng trong giao diện cấu hình.
- **Xử lý ngoại lệ Module**: Khi gọi controller của module khác (như `supplychain`), luôn sử dụng các thuộc tính `if-module` hoặc kiểm tra `isApp()` trong XML để tránh lỗi `ClassNotFoundException` nếu module đó không được cài đặt.
- **Dữ liệu Demo**: Các thay đổi về menu trong XML cần được đồng bộ với dữ liệu khởi tạo (`meta_metaMenu.csv`) để tránh lỗi khi người dùng thực hiện Import Demo Data.
- **Độ chính xác trong Build Gradle**: Một lỗi nhỏ về cú pháp (như dư dấu nháy) trong `build.gradle` có thể làm hỏng toàn bộ quá trình xây dựng của project.
