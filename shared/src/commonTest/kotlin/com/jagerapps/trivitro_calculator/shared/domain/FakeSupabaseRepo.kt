package com.jagerapps.trivitro_calculator.shared.domain

import com.jaegerapps.trivitro_calculator.core.domain.util.Resource
import com.jaegerapps.trivitro_calculator.shared.domain.SupabaseRepo
import com.jaegerapps.trivitro_calculator.shared.domain.models.Faq
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter

class FakeSupabaseRepo : SupabaseRepo {
    private val _calculations = listOf(
        PoolFilter("Pentair", "TR40", 175, 140, 0, 0, 3),
        PoolFilter("Pentair", "TR50", 225, 180, 0, 0, 4),
        PoolFilter("Pentair", "TR60", 325, 260, 182, 78, 4),
    )
    private val _faqs = listOf(
        Faq(
            "How do I install this?",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        ),
        Faq(
            "Why is this good",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        ),
        Faq(
            "Where can i purchase this?",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        ),
        Faq(
            "Product difference?",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        ),
        Faq(
            "Where can I find the calculatios?",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        ),
        Faq(
            "How are the calculations done?",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vel volutpat eros. Donec sollicitudin turpis posuere est feugiat, sit amet commodo dui dignissim. Quisque bibendum, nisi in venenatis tristique, mauris lacus commodo erat, vel fermentum turpis velit at magna. Sed nec velit enim. In euismod, enim efficitur bibendum volutpat, dui nibh facilisis mauris, in luctus tellus sem ac tellus. Nulla vitae nisl vulputate ante congue venenatis in quis neque. Maecenas vulputate velit ac scelerisque bibendum. Mauris vel dui rutrum arcu lobortis tincidunt. Vivamus vel purus urna. Nullam in urna mi. Morbi nunc metus, blandit at elit at, aliquet varius turpis."
        )
    )

    override suspend fun getCalculations(): Resource<List<PoolFilter>> {
        return Resource.Success(_calculations)

    }

    override suspend fun getFaqs(): Resource<List<Faq>> {
        return Resource.Success(_faqs)

    }

}