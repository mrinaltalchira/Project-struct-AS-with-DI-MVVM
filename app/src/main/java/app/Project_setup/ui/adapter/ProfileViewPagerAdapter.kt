//package app.Project_setup.ui.adapter
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.lifecycle.Lifecycle
//import androidx.viewpager2.adapter.FragmentStateAdapter
//
//
//class ViewPagerAdapter(parentFragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(parentFragmentManager, lifecycle) {
//
//    private val tabCount = 3
//
//    override fun getItemCount(): Int {
//        return tabCount
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        return when (position) {
//            0 -> PostImage()
//            1 -> PostText()
//            else -> PeopleTag()
//        }
//    }
//}