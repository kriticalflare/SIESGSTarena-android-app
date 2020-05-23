package com.kriticalflare.siesgstarena.blogs.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kriticalflare.siesgstarena.blogs.repository.BlogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BlogsViewModel(
    private val blogsRepository: BlogsRepository
) : ViewModel() {
    fun getAllBlogs() = blogsRepository.getAllBlogs()

    fun refreshBlogs(){
        viewModelScope.launch(Dispatchers.IO) {
            blogsRepository.refreshBlogs()
        }
    }
}