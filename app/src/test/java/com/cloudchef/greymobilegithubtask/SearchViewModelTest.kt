@file:OptIn(ExperimentalCoroutinesApi::class)

package com.cloudchef.greymobilegithubtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.domain.model.GithubRepoModel
import com.cloudchef.greymobilegithubtask.domain.model.Owner
import com.cloudchef.greymobilegithubtask.domain.model.Repository
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import com.cloudchef.greymobilegithubtask.presentation.search_repository.SearchEvent
import com.cloudchef.greymobilegithubtask.presentation.search_repository.SearchViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class SearchViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: SearchViewModel
    private val repository: GithubRepository = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onEvent with OnSearchQueryChange should update state and trigger search`() = runTest {
        val searchQuery = "kotlin"
        val name = "Victor"
        val list = listOf(
            Repository(
                1, "Victor", "Ezinwa Victor",
                Owner("Somto", 1, "https://Image.png", "organisation"),
                false, "", fork = false, defaultBranch = "",
                url = "", stargazersCount = 20, watchersCount = 60,
                forksCount = 10, openIssuesCount = 20, size = 20,
                pushedAt = "", createdAt = "", updatedAt = "", topics = listOf()))

        val listSize =  list.size
        val userRepos = GithubRepoModel(20, false, list)

        coEvery { repository.fetchRepo(searchQuery.lowercase()) } returns flowOf(
            Resource.Loading(false),
            Resource.Success(userRepos)
        )

        viewModel.onEvent(SearchEvent.OnSearchQueryChange(searchQuery))

        advanceTimeBy(500L)
        runCurrent()

        coVerify { repository.fetchRepo(searchQuery.lowercase()) }
        assertEquals(listSize, viewModel.state.user!!.items.size)
        assertEquals(name, viewModel.state.user!!.items[0].name )
        assertEquals(searchQuery, viewModel.state.searchQuery)
        assertEquals(userRepos, viewModel.state.user)
    }
}