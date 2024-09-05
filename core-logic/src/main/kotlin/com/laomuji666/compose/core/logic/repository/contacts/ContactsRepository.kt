package com.laomuji666.compose.core.logic.repository.contacts

import android.content.Context
import com.laomuji666.compose.res.R
import kotlinx.coroutines.flow.flow

class ContactsRepository(
    private val context: Context
) {
    fun contactsList() = flow{
        val result = ArrayList<ContactInfo>()
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_ragdoll_cat),
                category = context.getString(R.string.string_contact_type_animal_pet),
                resId = R.drawable.avatar_ragdoll_cat
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_golden_dog),
                category = context.getString(R.string.string_contact_type_animal_pet),
                resId = R.drawable.avatar_golden_dog
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_parrot),
                category = context.getString(R.string.string_contact_type_animal_pet),
                resId = R.drawable.avatar_parrot
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_siberian_husky),
                category = context.getString(R.string.string_contact_type_animal_pet),
                resId = R.drawable.avatar_siberian_husky
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_british_shorthair),
                category = context.getString(R.string.string_contact_type_animal_pet),
                resId = R.drawable.avatar_british_shorthair
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_sheep),
                category = context.getString(R.string.string_contact_type_animal_wild),
                resId = R.drawable.avatar_sheep
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_tiger),
                category = context.getString(R.string.string_contact_type_animal_wild),
                resId = R.drawable.avatar_tiger
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_mouse),
                category = context.getString(R.string.string_contact_type_animal_wild),
                resId = R.drawable.avatar_mouse
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_giraffe),
                category = context.getString(R.string.string_contact_type_animal_wild),
                resId = R.drawable.avatar_giraffe
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_eagle),
                category = context.getString(R.string.string_contact_type_animal_wild),
                resId = R.drawable.avatar_eagle
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_wolf),
                category = context.getString(R.string.string_contact_type_animal_wild),
                resId = R.drawable.avatar_wolf
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_leopard),
                category = context.getString(R.string.string_contact_type_animal_wild),
                resId = R.drawable.avatar_leopard
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_bee),
                category = context.getString(R.string.string_contact_type_insect),
                resId = R.drawable.avatar_bee
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_butterfly),
                category = context.getString(R.string.string_contact_type_insect),
                resId = R.drawable.avatar_butterfly
            )
        )
        result.add(
            ContactInfo.InnerContactInfo(
                nickname = context.getString(R.string.string_contact_name_mantis),
                category = context.getString(R.string.string_contact_type_insect),
                resId = R.drawable.avatar_mantis
            )
        )
        emit(result)
    }
}