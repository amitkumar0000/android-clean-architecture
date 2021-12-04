package com.android.core.data

import javax.inject.Inject

data class Note @Inject constructor(var title: String, var content: String, var creationTime: Long, var updateTime: Long, var id: Long=0)