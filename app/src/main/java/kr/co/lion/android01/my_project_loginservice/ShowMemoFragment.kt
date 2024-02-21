package kr.co.lion.android01.my_project_loginservice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentShowMemoBinding
import kr.co.lion.android01.my_project_loginservice.databinding.RowBinding

class ShowMemoFragment : Fragment() {

    lateinit var fragmentShowMemoBinding: FragmentShowMemoBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowMemoBinding = FragmentShowMemoBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        initView()
        setToolBar()
        floatButton()
        return fragmentShowMemoBinding.root
    }
    //툴바 설정
    fun setToolBar(){
        fragmentShowMemoBinding.apply {
            materialToolbar6.apply {
                title = "나의 운동 메모"
                setNavigationIcon(R.drawable.finmyloggo_gh)
                //메뉴
                inflateMenu(R.menu.show_memo_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.filter_menu -> {

                        }
                    }

                    true
                }
            }
            bottomAppBar.apply {
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭했을 때
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.SHOW_MEMO_FRAGMENT)
                }
            }
        }
    }

    //floatingAction 버튼
    fun floatButton(){
        fragmentShowMemoBinding.apply {
            floatingActionButton.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.RECORD_MEMO_FRAGMENT, true, true, null)
            }
        }
    }

    fun initView(){
        fragmentShowMemoBinding.apply {
            recyclerview2.apply {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                var deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    //recyclerView
    inner class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        //viewHolderClass
        inner class ViewHolderClass(rowBinding: RowBinding):RecyclerView.ViewHolder(rowBinding.root){
            var rowBinding:RowBinding
            init {
                this.rowBinding = rowBinding
                //가로 세로 길이
                this.rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            var rowBinding = RowBinding.inflate(layoutInflater)
            var viewHolder = ViewHolderClass(rowBinding)
            return viewHolder
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.textShowDate.text = "2월 21일"
            holder.rowBinding.textShowExercise.text = "가슴"
            //클릭했을 때
            holder.rowBinding.root.setOnClickListener {
                var bottomShowFragment = BottomShowFragment()

                //position 번째 추출

                //보여주기
                bottomShowFragment.show(mainActivity.supportFragmentManager, "BottomSheet")
            }
        }
    }
}
































