package com.apolis.groceryapplication1.database

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.apolis.groceryapplication1.models.OrderResponse
import com.apolis.groceryapplication1.models.OrderSummary
import com.apolis.groceryapplication1.models.Product

class DBHelper(mContext: Context) : SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {

    var database = writableDatabase

    companion object{
        const val DATABASE_NAME = "CartDB"
        const val TABLE_NAME = "cart"
        const val DATABASE_VERSION = 2
        const val COL_PID = "pid"
        const val COL_NAME = "name"
        const val COL_PRICE = "price"
        const val COL_MRP = "mrp"
        const val COL_IMAGE = "image"
        const val COL_QUANTITY = "quantity"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("db", "onCreate")
        var createTable = "create table $TABLE_NAME ($COL_PID char(200), $COL_NAME char(100), $COL_PRICE char(50), $COL_MRP char(50), $COL_IMAGE char(200), $COL_QUANTITY char(50))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("db", "onUpgrade")
        var dropTable = "DROP table employee"
//        var alterTable = "alter table $TABLE_NAME add $COL_PHONE CHAR(50)"
        db?.execSQL(dropTable)
        onCreate(db)
    }

    // getReadable() - read permission / rights
    // getWriteable() - read and write permission
    // insert into employee values (1, 'mark', 'm@gmail.com', '9999')

    fun addToCart(product: Product){
        if(!isProductInCart(product)){
            var contentValue = ContentValues()
            contentValue.put(COL_PID, product._id)
            contentValue.put(COL_NAME, product.productName)
            contentValue.put(COL_PRICE, product.price)
            contentValue.put(COL_MRP, product.mrp)
            contentValue.put(COL_IMAGE, product.image)
            contentValue.put(COL_QUANTITY, product.qty)
            var result = database.insert(TABLE_NAME, null, contentValue)
            Log.d("RESULT", result.toString())
        }

    }
    private fun isProductInCart(product: Product): Boolean{
        var query = "select * from $TABLE_NAME where $COL_PID = ?"
        var cursor = database.rawQuery(query,arrayOf(product._id))
        var count = cursor.count
        return count != 0
    }

    // delete from employee where id = 1
    fun deleteProduct(id: String){
        val whereClause = "$COL_PID = ?"
        val whereArgs = arrayOf(id)
        database.delete(TABLE_NAME, whereClause, whereArgs)
        //db.delete(TABLE_NAME, "id = ?", arrayOf(id.toString()))
    }

    fun getCartQuantity(): Long {
        return DatabaseUtils.queryNumEntries(database, "$TABLE_NAME")
    }

    // update employee set name = 'mark 2', email = 'm2@gmail.com' where id = 1
    fun updateProduct(updateProduct: Product){
        val whereClause = "$COL_PID = ?"
        val whereArgs = arrayOf(updateProduct._id)
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, updateProduct.productName)
        contentValues.put(COL_PRICE, updateProduct.price)
        contentValues.put(COL_MRP, updateProduct.mrp)
        contentValues.put(COL_IMAGE, updateProduct.image)
        contentValues.put(COL_QUANTITY, updateProduct.qty)
        database.update(TABLE_NAME, contentValues, whereClause, whereArgs)
    }

    //update the quantity for a product
    fun updateQuantity(updateProduct: Product, quantity: Int){
        var whereClause = "$COL_PID = ?"
        val whereArgs = arrayOf(updateProduct._id)
        var contentValues = ContentValues()
        contentValues.put(COL_QUANTITY, updateProduct.qty)
        database.update(TABLE_NAME, contentValues, whereClause, whereArgs)
    }

    // select * from employee
    // select id, name, email from employee
    // select * from employee where id=1
    // select id,name from employee where id=1
    fun readCart(): ArrayList<Product>{
        var productList: ArrayList<Product> = ArrayList()
        var columns = arrayOf(
                COL_PID,
                COL_NAME,
                COL_PRICE,
                COL_MRP,
                COL_IMAGE,
                COL_QUANTITY
        )
        var cursor = database.query(TABLE_NAME, columns, null, null, null, null, null)
        if(cursor !=null && cursor.moveToFirst()){
            do{
                var pid = cursor.getString(cursor.getColumnIndex(COL_PID))
                var name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                var price = cursor.getDouble(cursor.getColumnIndex(COL_PRICE))
                var mrp = cursor.getDouble(cursor.getColumnIndex(COL_MRP))
                var image = cursor.getString(cursor.getColumnIndex(COL_IMAGE))
                var quantity = cursor.getInt(cursor.getColumnIndex(COL_QUANTITY))
                var product = Product(_id=pid, productName = name, price=price, mrp=mrp, image=image, qty=quantity)
                productList.add(product)

            }while (cursor.moveToNext())
        }
        cursor.close()
        return productList
    }

    ////////////Change to OrderSummary class type/////////
    fun checkoutTotal(): ArrayList<OrderSummary> {
        var deliveryCharges: Double = 0.0
        var discount: Double = 0.0
        var orderAmount: Double = 0.0
        var ourPrice: Double = 0.0
        var orderSumList: ArrayList<OrderSummary> = ArrayList()
        var columns = arrayOf(
                COL_PRICE,
                COL_MRP,
                COL_QUANTITY
        )
        var cursor = database.query(TABLE_NAME, columns, null, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                var quantity = cursor.getInt(cursor.getColumnIndex(COL_QUANTITY))
                var price = cursor.getDouble(cursor.getColumnIndex(COL_PRICE))
                var mrp = cursor.getDouble(cursor.getColumnIndex(COL_MRP))
                orderAmount += quantity * price
                discount += quantity * (mrp - price)
                deliveryCharges += quantity * price * 0.02
                ourPrice += (quantity * price) - (quantity * (mrp - price)) - (quantity * price * 0.02)
                var orderSummary = OrderSummary(orderAmount=orderAmount, discount=discount, deliveryCharges=deliveryCharges, ourPrice=ourPrice)
                orderSumList.add(orderSummary)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return orderSumList
    }

    fun getTotal(): ArrayList<Double> {
        var subtotal: Double = 0.0
        var saving: Double = 0.0
        var tax: Double = 0.0
        var total: Double = 0.0
        var columns = arrayOf(
                COL_PRICE,
                COL_MRP,
                COL_QUANTITY
        )
        var cursor = database.query(TABLE_NAME, columns, null, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                var quantity = cursor.getInt(cursor.getColumnIndex(COL_QUANTITY))
                var price = cursor.getDouble(cursor.getColumnIndex(COL_PRICE))
                var mrp = cursor.getDouble(cursor.getColumnIndex(COL_MRP))
                subtotal += quantity * price
                saving += quantity * (mrp - price)
                tax += quantity * price * 0.02
                total += (quantity * price) - (quantity * (mrp - price)) + (quantity * price * 0.02)
            } while (cursor.moveToNext())
            cursor.close()

        }
        var arrayList = ArrayList<Double>()
        arrayList.add(subtotal)
        arrayList.add(saving)
        arrayList.add(tax)
        arrayList.add(total)
        return arrayList
    }

    fun clearCartContent() {
        database.delete(TABLE_NAME, null, null)
    }

}