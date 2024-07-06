package com.cloudchef.greymobilegithubtask.data.remote.dto

import com.cloudchef.greymobilegithubtask.data.remote.dto.OwnerDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUserListDto(
    @SerialName("total_count")
    val totalCount: Int,

    @SerialName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<OwnerDto>
) {
  companion object {
      fun GithubUserListDto.toDomain() : GithubUserList {
         return GithubUserList(totalCount, incompleteResults, items.map { it.toDomain() })
      }
  }
}


