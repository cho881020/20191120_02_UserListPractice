package com.tjoeun.a20191120_02_userlistpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tjoeun.a20191120_02_userlistpractice.datas.Category
import com.tjoeun.a20191120_02_userlistpractice.utils.ConnectServer
import org.json.JSONObject

class UserDetailActivity : BaseActivity() {

    var categoryList = ArrayList<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        getCategoryListFromServer()
    }

    fun  getCategoryListFromServer() {

        ConnectServer.getRequestCategoryList(mContext, object : ConnectServer.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {
                Log.d("카테고리응답", json.toString())

                var code = json.getInt("code")

                if (code == 200) {
                    var data = json.getJSONObject("data")
                    var userCategories = data.getJSONArray("user_categories")

                    categoryList.clear()

                    for (i in 0..userCategories.length() - 1) {
                        var uc = userCategories.getJSONObject(i)
                        var categoryData = Category.getCategoryFromJson(uc)
                        categoryList.add(categoryData)
                    }

                    runOnUiThread {
//                        스피너 새로고침 필요
                    }

                }

            }

        })

    }

}
