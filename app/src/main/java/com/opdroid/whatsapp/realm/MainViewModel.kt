package com.opdroid.whatsapp.realm

/*class MainViewModel: ViewModel() {

    private val realm = MyApp.realm

    val courses = realm
        .query<Course>()
        .asFlow()
        .map { results ->
            results.list.toList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    var courseDetails: Course? by mutableStateOf(null)
        private set

    init {
//        createSampleEntries()
    }

    fun showCourseDetails(course: Course) {
        courseDetails = course
    }

    fun hideCourseDetails() {
        courseDetails = null
    }

    private fun createSampleEntries() {
        viewModelScope.launch {
            realm.write {
                val address1 = Address().apply {
                    fullName = "John Doe"
                    street = "John Doe Street"
                    houseNumber = 24
                    zipCode = 12345
                    city = "Johncity"
                }
                val address2 = Address().apply {
                    fullName = "Jane Doe"
                    street = "Jane Doe Street"
                    houseNumber = 25
                    zipCode= 12345
                    city = "Johncity"
                }

                val course1 = Course().apply {
                    name = "Kotlin Programming Made Easy"
                }
                val course2 = Course().apply {
                    name = "Android Basics"
                }
                val course3 = Course().apply {
                    name = "Asynchronous Programming With Coroutines"
                }

                val teacher1 = Teacher().apply {
                    address = address1
                    courses = realmListOf(course1, course2)
                }
                val teacher2 = Teacher().apply {
                    address = address2
                    courses = realmListOf(course3)
                }

                course1.teacher = teacher1
                course2.teacher = teacher1
                course3.teacher = teacher2

                address1.teacher = teacher1
                address2.teacher = teacher2

                val student1 = Student().apply {
                    name = "John Junior"
                }
                val student2 = Student().apply {
                    name = "Jane Junior"
                }

                course1.enrolledStudents.add(student1)
                course2.enrolledStudents.add(student2)
                course3.enrolledStudents.addAll(listOf(student1, student2))

                copyToRealm(teacher1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(teacher2, updatePolicy = UpdatePolicy.ALL)

                copyToRealm(course1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(course2, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(course3, updatePolicy = UpdatePolicy.ALL)

                copyToRealm(student1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(student2, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    fun deleteCourse() {
        viewModelScope.launch {
            realm.write {
                val course = courseDetails ?: return@write
                val latestCourse = findLatest(course) ?: return@write
                delete(latestCourse)

                courseDetails = null
            }
        }
    }*/

    // existing code...

    /*suspend fun saveContact(contact: MyContact) {
        withContext(Dispatchers.IO) {

            val realm = Realm.getInstance(MyApp.realmConfig) // Use the RealmConfiguration from your application class
            realm.executeTransaction { realmTransaction: Realm ->
                realmTransaction.copyToRealm(contact)
            }
            realm.close()

            realm.writeBlocking {
                copyToRealm(ContactRealm().apply {
                    this.contact = contact
                })
            }
        }
    }

    suspend fun saveChatToContact(contactId: String, chat: ChatRealm) {
        withContext(Dispatchers.IO) {
            realm.writeBlocking {
              val contact: ContactRealm = realm.query<ContactRealm>("id", contactId).find().first()
                contact.chatList.add(chat)
            }
        }
    }
    suspend fun chatListFromContact(contactId:String): List<ChatRealm> {
        return withContext(Dispatchers.IO) {
            realm.query<ContactRealm>("id", contactId).find().first().chatList
        }
    }

    suspend fun contactFromId(contactId: String): MyContact {
        return withContext(Dispatchers.IO) {
            realm.query<ContactRealm>("id", contactId).find().first().contact
        }
    }*/

//}
