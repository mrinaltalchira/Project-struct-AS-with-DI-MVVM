package app.Project_setup.data.remote


import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : BaseDataSource() {


    /*fun login(params: HashMap<String, Any>) = performOperation(ApiConstants.login) {
        getResult(ApiConstants.login) {
            apiService.login(params)
        }
    }*/

  /*  fun sendOTP(params: HashMap<String, Any>) = performOperation(ApiConstants.sendOTP) {
        getResult(ApiConstants.sendOTP) {
            apiService.sendOTP(params)
        }
    }*/



 /*   fun createPost(
        item: ArrayList<MediaItem>,
        description: String,
        post_type: String,
        tag_people: String,
        context: Context
    ) = performOperation("") {
        getResult("") {
            val listPartImages = ArrayList<MultipartBody.Part>()
            listPartImages.clear()
            item.forEach {
                val file = File(it.absolutePath)
                val newFile = Compressor.compress(context, file)
                val requestImageFile: RequestBody =
                    newFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                val partImage = MultipartBody.Part.createFormData(
                    "media[]",
                    newFile.name.toString(),
                    requestImageFile
                )
                listPartImages.add(partImage)
            }

            val description = MultipartBody.Part.createFormData("description", description)
            val postType = MultipartBody.Part.createFormData("post_type", post_type)
            val tagPeople = MultipartBody.Part.createFormData("tag_people", tag_people)
            apiService.createPost(
                listPartImages, description, postType, tagPeople
            )
        }
    }*/




}