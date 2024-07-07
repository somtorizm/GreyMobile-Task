package com.cloudchef.greymobilegithubtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import com.cloudchef.greymobilegithubtask.models.createGithubUserModel
import com.cloudchef.greymobilegithubtask.models.createUserRepos
import com.cloudchef.greymobilegithubtask.presentation.user_detail.GithubUserViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GithubUserViewModelTest  {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: GithubUserViewModel
    private val savedStateHandle = SavedStateHandle(mapOf("userId" to "victor"))

    private val repository: GithubRepository = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should fetch user and user repos`() = runTest {
        viewModel = GithubUserViewModel(repository, savedStateHandle)
        val model = createGithubUserModel()
        val userRepos = createUserRepos()

        coEvery { repository.fetchUser("victor") } returns flowOf(
            Resource.Loading(true),
            Resource.Success(model),
            Resource.Loading(false)
        )

        coEvery { repository.fetchUserRepo("victor") } returns flowOf(
            Resource.Loading(true),
            Resource.Success(userRepos),
            Resource.Loading(false)
        )

        advanceUntilIdle()

        coVerify { repository.fetchUser("victor") }
        coVerify { repository.fetchUserRepo("victor") }

        assertEquals(model, viewModel.state.user)
        assertEquals(userRepos, viewModel.state.usersRepo)
        assertEquals(false, viewModel.state.isLoading)
        assertEquals(null, viewModel.state.error)
    }
}