package onafy.kade_finalproject.Features.TeamDetail

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import onafy.kade_finalproject.DetailTeam.TeamDetailView
import onafy.kade_finalproject.ModelDataClass.Team
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx


class TeamDescFragment() : Fragment(), AnkoComponent<Context>, TeamDetailView {

    private lateinit var description: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        description.text = this.arguments?.getString("detailTeam") ?: "No Description for this team."
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        scrollView() {
            lparams (width = matchParent, height = wrapContent)
            leftPadding = dip(15)
            rightPadding = dip(15)

            isVerticalScrollBarEnabled = false
            description = textView{
                verticalPadding = dip(15)
                textSize = 15f
                gravity = Gravity.CENTER
                setLineSpacing(1F, 1.5F)
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamDetail(data: List<Team>) {
    }

}
