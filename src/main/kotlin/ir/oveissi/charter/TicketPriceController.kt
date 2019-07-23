package ir.oveissi.charter


import com.google.gson.Gson
import com.sun.xml.internal.ws.api.pipe.ContentType
import ir.oveissi.charter.template.GetTPs
import ir.oveissi.charter.template.TP
import okhttp3.*
import org.springframework.boot.jackson.JsonObjectDeserializer
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity.post
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TicketPriceController {

    @RequestMapping("/tickets")
    fun answer(): ResponseEntity<Any> {
        val client = OkHttpClient()


        val formBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("from", "10003")
                .addFormDataPart("to", "10000")
                .build()

        val request = Request.Builder()
                .url("http://ch724.info/get_query.html")
                .post(formBody)
                .build()

        val okhttpResponse = client.newCall(request).execute()

        if(okhttpResponse.isSuccessful) {
            val response = okhttpResponse.body()?.string()?.replace("<br>", "")

            val gson = Gson()
            val responseObj = gson.fromJson(response,GetTPs::class.java)

            val list = mutableListOf<TP>()

            responseObj.result.forEach {
                list.add(it.copy(
                        price = it.price.replace("تومان","").replace(",","")
                ))
            }
            if(response != null)
                return  ResponseEntity.ok(list)
        }

        val fakeTp = TP("0","1/8","جمعه","")
        val fakeList = mutableListOf<TP>()
        for (i in 1..10)
            fakeList.add(fakeTp)
        return  ResponseEntity.ok(fakeList)
    }
}