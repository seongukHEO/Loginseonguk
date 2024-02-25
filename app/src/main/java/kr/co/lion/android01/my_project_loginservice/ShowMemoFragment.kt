package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentShowMemoBinding
import kr.co.lion.android01.my_project_loginservice.databinding.RowBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum

class ShowMemoFragment : Fragment() {

    lateinit var fragmentShowMemoBinding: FragmentShowMemoBinding
    lateinit var mainActivity: MainActivity

    //UserMemoInfoClass의 값을 받을 객체
    //lateinit var memoList:MutableList<UserMemoInfoClass>
    lateinit var memoList:MutableList<MemoClass>

    var filterDialog = ShowFilter.FILTER_ALL

    var memoList2 = mutableListOf<MemoClass>()
    var memoListIndex = mutableListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowMemoBinding = FragmentShowMemoBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        initView()
        setToolBar()
        floatButton()
        saveData()
        return fragmentShowMemoBinding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentShowMemoBinding.apply {
            showResult()
            recyclerview2.adapter?.notifyDataSetChanged()
            enum.hideSoftInput(mainActivity)
        }
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
                            showFilterDiaLog()

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
    fun saveData(){
        //var str = arguments?.getString("userId")
        memoList = MemoDAO.selectAllMemo(mainActivity)



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
            return memoList2.size

        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.textShowId.text = "아이디 : ${memoList2[position].userId}"
            holder.rowBinding.textShowDate.text = "운동 시간 : ${memoList2[position].exerciseTime}분"
            when(memoList2[position].exerciseBody){
                ExerciseBody.CHEST.num -> {
                    holder.rowBinding.textShowExercise.text = "운동 부위 : ${ExerciseBody.CHEST.str}"
                }
                ExerciseBody.BACK.num -> {
                    holder.rowBinding.textShowExercise.text = "운동 부위 : ${ExerciseBody.BACK.str}"
                }
                ExerciseBody.SHOULDER.num -> {
                    holder.rowBinding.textShowExercise.text = "운동 부위 : ${ExerciseBody.SHOULDER.str}"
                }
                ExerciseBody.ARMS.num -> {
                    holder.rowBinding.textShowExercise.text = "운동 부위 : ${ExerciseBody.ARMS.str}"
                }
                ExerciseBody.LEGS.num -> {
                    holder.rowBinding.textShowExercise.text = "운동 부위 : ${ExerciseBody.LEGS.str}"
                }
                ExerciseBody.EXTRA.num -> {
                    holder.rowBinding.textShowExercise.text = "운동 부위 : ${ExerciseBody.EXTRA.str}"
                }
            }
            //클릭했을 때
            holder.rowBinding.root.setOnClickListener {
                var bottomShowFragment = BottomShowFragment()

                //position 번째 추출
                var bundle = Bundle()
                bundle.putString("userId", memoList2[position].userId)
                bottomShowFragment.arguments = bundle

                //보여주기
                bottomShowFragment.show(mainActivity.supportFragmentManager, "BottomSheet")
            }
        }
    }
    fun reloadRecyclerView(){
        //데이터를 읽어온다
        memoList2 = MemoDAO.selectAllMemo(mainActivity)
        //RecyclerView를 갱신한다
        fragmentShowMemoBinding.recyclerview2.adapter?.notifyDataSetChanged()
    }

    //필터 생성
    fun showFilterDiaLog(){
        var diaLogMemo = MaterialAlertDialogBuilder(mainActivity)
        diaLogMemo.setTitle("필터 선택")

        var memoArray = arrayOf("전체", "가슴", "등", "어깨", "팔", "하체", "기타")
        diaLogMemo.setItems(memoArray){ dialogInterface: DialogInterface, i: Int ->
            filterDialog = when(i){
                0 -> ShowFilter.FILTER_ALL
                1 -> ShowFilter.FILTER_CHEST
                2 -> ShowFilter.FILTER_BACK
                3 -> ShowFilter.FILTER_SHOULDER
                4 -> ShowFilter.FILTER_ARMS
                5 -> ShowFilter.FILTER_LEGS
                6 -> ShowFilter.FILTER_EXTRA
                else -> ShowFilter.FILTER_ALL
            }
            //정보를 가져온다
            showResult()

            fragmentShowMemoBinding.recyclerview2.adapter?.notifyDataSetChanged()
        }
        diaLogMemo.setPositiveButton("확인", null)
        diaLogMemo.show()
    }

    fun showResult(){
        //싹 비워준다
        memoList2.clear()
        memoListIndex.clear()
        //타입별로 분기한다
        when(filterDialog){
            ShowFilter.FILTER_ALL -> {
                memoList.forEachIndexed { index, memoClass ->
                    memoList2.add(memoClass)
                    memoListIndex.add(index)
                }
            }
            ShowFilter.FILTER_CHEST -> {
                memoList.forEachIndexed { index, memoClass ->
                    if (memoClass.exerciseBody == ExerciseBody.CHEST.num){
                        memoList2.add(memoClass)
                        memoListIndex.add(index)
                    }
                }

            }
            ShowFilter.FILTER_BACK -> {
                memoList.forEachIndexed { index, memoClass ->
                    if (memoClass.exerciseBody == ExerciseBody.BACK.num){
                        memoList2.add(memoClass)
                        memoListIndex.add(index)
                    }
                }
            }
            ShowFilter.FILTER_SHOULDER -> {
                memoList.forEachIndexed { index, memoClass ->
                    if (memoClass.exerciseBody == ExerciseBody.SHOULDER.num){
                        memoList2.add(memoClass)
                        memoListIndex.add(index)
                    }
                }
            }
            ShowFilter.FILTER_ARMS -> {
                memoList.forEachIndexed { index, memoClass ->
                    if (memoClass.exerciseBody == ExerciseBody.ARMS.num){
                        memoList2.add(memoClass)
                        memoListIndex.add(index)
                    }
                }
            }
            ShowFilter.FILTER_LEGS -> {
                memoList.forEachIndexed { index, memoClass ->
                    if (memoClass.exerciseBody == ExerciseBody.LEGS.num){
                        memoList2.add(memoClass)
                        memoListIndex.add(index)
                    }
                }
            }
            ShowFilter.FILTER_EXTRA -> {
                memoList.forEachIndexed { index, memoClass ->
                    if (memoClass.exerciseBody == ExerciseBody.EXTRA.num){
                        memoList2.add(memoClass)
                        memoListIndex.add(index)
                    }
                }
            }
        }
    }


}
































