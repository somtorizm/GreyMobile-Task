package com.cloudchef.greymobilegithubtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.domain.model.GithubRepoModel
import com.cloudchef.greymobilegithubtask.domain.model.Owner
import com.cloudchef.greymobilegithubtask.domain.model.Repository
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import com.cloudchef.greymobilegithubtask.models.createUserRepos
import com.cloudchef.greymobilegithubtask.presentation.search_repository.SearchEvent
import com.cloudchef.greymobilegithubtask.presentation.search_repository.SearchViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SearchRepoViewModelTest {
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
    fun `onEvent Search should update state and trigger search`() = runTest {
        val searchQuery = "kotlin"
        val name = "Victor"
        val list = createUserRepos()

        val userRepos = GithubRepoModel(20, false, list)

        coEvery { repository.fetchRepo(searchQuery.lowercase()) } returns flowOf(
            Resource.Loading(false),
            Resource.Success(userRepos)
        )

        viewModel.onEvent(SearchEvent.OnSearchQueryChange(searchQuery))

        advanceTimeBy(500L)
        runCurrent()

        coVerify { repository.fetchRepo(searchQuery.lowercase()) }
        assertEquals(list.size, viewModel.state.user!!.items.size)
        assertEquals(name, viewModel.state.user!!.items[0].name )
        assertEquals(searchQuery, viewModel.state.searchQuery)
        assertEquals(userRepos, viewModel.state.user)
    }

    @Test
    fun `onEvent should handle Resource_Loading and update state`() = runTest {
        val searchQuery = "victor"

        coEvery { repository.fetchRepo(searchQuery.lowercase()) } returns flowOf(
            Resource.Loading(true)
        )

        viewModel.onEvent(SearchEvent.OnSearchQueryChange(searchQuery))

        advanceTimeBy(500L)
        runCurrent()

        coVerify { repository.fetchRepo(searchQuery.lowercase()) }
        TestCase.assertEquals(searchQuery, viewModel.state.searchQuery)
        TestCase.assertEquals(true, viewModel.state.isLoading)
    }

    @Test
    fun `onEvent should handle Resource_Error and update state`() = runTest {
        val searchQuery = "kotlin"
        val errorMessage = "An error occurred"

        coEvery { repository.fetchRepo(searchQuery.lowercase()) } returns flowOf(
            Resource.Loading(false),
            Resource.Error(errorMessage)
        )

        viewModel.onEvent(SearchEvent.OnSearchQueryChange(searchQuery))

        advanceTimeBy(500L)
        runCurrent()

        coVerify { repository.fetchRepo(searchQuery.lowercase()) }
        TestCase.assertEquals(searchQuery, viewModel.state.searchQuery)
        TestCase.assertEquals(null, viewModel.state.user)
    }

    @Test
    fun `onCleared should cancel searchJob`() = runTest {
        viewModel.onCleared()
        TestCase.assertEquals(null, viewModel.searchJob)
    }
}