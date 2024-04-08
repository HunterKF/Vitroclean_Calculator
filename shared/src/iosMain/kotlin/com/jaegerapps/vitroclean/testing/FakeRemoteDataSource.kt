package com.jaegerapps.vitroclean.testing

import com.jaegerapps.vitroclean.core.domain.util.Resource
import com.jaegerapps.vitroclean.shared.data.remote.RemoteDataSource
import com.jaegerapps.vitroclean.shared.data.remote.dtos.FaqDto
import com.jaegerapps.vitroclean.shared.data.remote.dtos.PoolFilterDto
import com.jaegerapps.vitroclean.shared.domain.models.Faq
import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter

class FakeRemoteDataSource: RemoteDataSource {

    private val faqsExample = listOf(
        FaqDto(
            1,
            "How do I install this?",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        ),
        FaqDto(
            2,
            "Why is this good",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        ),
        FaqDto(
            3,
            "Where can i purchase this?",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        ),
        FaqDto(
            4,
            "Product difference?",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        ),
        FaqDto(
            5,
            "Where can I find the calculatios?",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        ),
        FaqDto(
            6,
            "How are the calculations done?",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        )
    )
    private val filterExample = listOf(
        PoolFilterDto(1, "Pentair", "TR40", 175, 140, 0, 0, 3),
        PoolFilterDto(2, "Pentair", "TR50", 225, 180, 0, 0, 4),
        PoolFilterDto(3, "Pentair", "TR60", 325, 260, 182, 78, 4),
    )

    override suspend fun getFiltersFromDb(): Resource<List<PoolFilterDto>> {
        return Resource.Success(filterExample)
    }

    override suspend fun getFaqsFromDb(): Resource<List<FaqDto>> {
        return Resource.Success(faqsExample)
    }
}