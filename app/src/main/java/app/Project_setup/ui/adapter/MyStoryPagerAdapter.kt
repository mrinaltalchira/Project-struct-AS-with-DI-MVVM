//package app.Project_setup.ui.adapter
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentStatePagerAdapter
//import androidx.viewpager.widget.ViewPager
//
//class MyStoryPagerAdapter (fragmentManager: FragmentManager, private val storyList: ArrayList<Stories.MyStory>)
//    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//
//    override fun getItem(position: Int): Fragment =
//        MyStoryViewFragment.newInstance(position, storyList[position])
//
//
//    override fun getCount(): Int {
//        return storyList.size
//    }
//
//    fun findFragmentByPosition(viewPager: ViewPager, position: Int): Fragment? {
//        println("STORIESADAPTER $storyList")
//        try {
//            val f = instantiateItem(viewPager, position)
//            return f as? Fragment
//        } finally {
//            finishUpdate(viewPager)
//        }
//    }
//}