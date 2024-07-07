package com.cloudchef.greymobilegithubtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserList
import com.cloudchef.greymobilegithubtask.domain.model.Owner
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import com.cloudchef.greymobilegithubtask.presentation.search_repository.SearchEvent
import com.cloudchef.greymobilegithubtask.presentation.search_user.SearchUserViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchUserViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: SearchUserViewModel
    private val repository: GithubRepository = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchUserViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onEvent with OnSearchQueryChange should update state and trigger search`() = runTest {
        val searchQuery = "ezinwa"

        val model = Owner("Victor Ezinwa", 234, "https://vector.png", "")
        val userListings = listOf(model, model, model)
        val modelList = GithubUserList(totalCount = 3, false, userListings)


        coEvery { repository.searchUser(searchQuery.lowercase()) } returns flowOf(
            Resource.Loading(false),
            Resource.Success(modelList)
        )

        viewModel.onEvent(SearchEvent.OnSearchQueryChange(searchQuery))

        advanceTimeBy(500L)

        runCurrent()

        coVerify { repository.searchUser(searchQuery.lowercase()) }
        assertEquals(searchQuery, viewModel.state.searchQuery)
        assertEquals(modelList, viewModel.state.user)
    }

    @Test
    fun `onEvent should handle Resource_Loading and update state`() = runTest {
        val searchQuery = "victor"

        coEvery { repository.searchUser(searchQuery.lowercase()) } returns flowOf(
            Resource.Loading(true)
        )

        viewModel.onEvent(SearchEvent.OnSearchQueryChange(searchQuery))

        advanceTimeBy(500L)
        runCurrent()

        coVerify { repository.searchUser(searchQuery.lowercase()) }
        assertEquals(searchQuery, viewModel.state.searchQuery)
        assertEquals(true, viewModel.state.isLoading)
    }

    @Test
    fun `onEvent should handle Resource_Error and update state`() = runTest {
        val searchQuery = "kotlin"
        val errorMessage = "An error occurred"

        coEvery { repository.searchUser(searchQuery.lowercase()) } returns flowOf(
            Resource.Loading(false),
            Resource.Error(errorMessage)
        )

        viewModel.onEvent(SearchEvent.OnSearchQueryChange(searchQuery))

        advanceTimeBy(500L)
        runCurrent()

        coVerify { repository.searchUser(searchQuery.lowercase()) }
        assertEquals(searchQuery, viewModel.state.searchQuery)
        assertEquals(null, viewModel.state.user)
    }

    @Test
    fun `onCleared should cancel searchJob`() = runTest {
        viewModel.onCleared()
        assertEquals(null, viewModel.searchJob)
    }
}