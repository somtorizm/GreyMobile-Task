package com.cloudchef.greymobilegithubtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel
import com.cloudchef.greymobilegithubtask.domain.model.Owner
import com.cloudchef.greymobilegithubtask.domain.model.Repository
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import com.cloudchef.greymobilegithubtask.presentation.search_user.SearchUserViewModel
import com.cloudchef.greymobilegithubtask.presentation.user_detail.GithubUserViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
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
        viewModel = GithubUserViewModel(repository, savedStateHandle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should fetch user and user repos`() = runTest {
        val model = GithubUserModel(
            login = "Somto",
            id = 1,
            nodeId = "1",
            avatarUrl = "https://victor.png",
            url = "",
            followersUrl = "",
            receivedEventsUrl = "",
            reposUrl = "",
            organizationsUrl = "",
            eventsUrl = "",
            subscriptionsUrl = "",
            gistsUrl = "",
            starredUrl = "",
            siteAdmin = false,
            htmlUrl = "",
            type = "",
            publicGists = 20,
            followers = 20,
            following = 45,
            createdAt = "",
            updatedAt = "",
            publicRepos = 3,
            hireable = false,
            email = "",
            location = "",
            blog = "",
            company = "",
            twitterUsername = "",
            gravatarId = "",
            followingUrl = ""
        )

        val userRepos = listOf(Repository(
                    1, "Victor", "Ezinwa Victor",
                    Owner("Somto", 1, "https://Image.png", "organisation"),
                    false, "", fork = false, defaultBranch = "",
                    url = "", stargazersCount = 20, watchersCount = 60,
                    forksCount = 10, openIssuesCount = 20, size = 20,
                    pushedAt = "", createdAt = "", updatedAt = "", topics = listOf()))

        coEvery { repository.fetchUser("victor") } returns Resource.Success(model)
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