package kr.co.lion.android01.my_project_loginservice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentShowBinding
import kr.co.lion.android01.my_project_loginservice.databinding.ShowRecyclerBinding

class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        initView()
        setToolBar()
        setEvent()
        return fragmentShowBinding.root
    }

    //툴바 설정
    fun setToolBar(){
        fragmentShowBinding.apply {
            materialToolbar3.apply {
                title = "나의 정보"
                setNavigationIcon(R.drawable.finmyloggo_gh)
            }
        }
    }

    //이벤트 설정
    fun setEvent(){
        fragmentShowBinding.apply {
            infoButton.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.INPUT_FRAGMENT, true ,true , null)
            }
            mymenuButton.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.SHOW_MEMO_FRAGMENT, true , true , null)
            }

        }
    }

    fun initView(){
        fragmentShowBinding.apply {
            recyclerview.apply {
                adapter = RecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                var deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    //리사이클러뷰
    inner class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        //viewHolderClass
        inner class ViewHolderClass(showRecyclerBinding: ShowRecyclerBinding):RecyclerView.ViewHolder(showRecyclerBinding.root){
            var showRecyclerBinding:ShowRecyclerBinding

            init {
                this.showRecyclerBinding = showRecyclerBinding
                //가로 세로 길이 맞추기
                this.showRecyclerBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            var showRecyclerBinding = ShowRecyclerBinding.inflate(layoutInflater)
            var viewHolder = ViewHolderClass(showRecyclerBinding)
            return viewHolder
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.showRecyclerBinding.textrecyclerHeight.text = "키 : 170cm"
            holder.showRecyclerBinding.textrecyclerWeight.text = "몸무게 : 80kg"
            holder.showRecyclerBinding.textrecyclerAge.text = "나이 : 24살"
            holder.showRecyclerBinding.textrecyclerBMI.text = "BMI : 20"
            holder.showRecyclerBinding.textrecyclerBone.text = "골격근량 : 39kg"
            //클릭했을 떄
            holder.showRecyclerBinding.root.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.MODIFY_FRAGMENT, true, true, null)
            }
        }
    }


}















































