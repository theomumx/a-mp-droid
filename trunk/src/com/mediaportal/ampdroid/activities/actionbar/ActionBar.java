/*
 * Copyright (C) 2010 Johan Nilsson <http://markupartist.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mediaportal.ampdroid.activities.actionbar;

import java.util.LinkedList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mediaportal.ampdroid.R;
public class ActionBar extends RelativeLayout implements OnClickListener {

   private LayoutInflater mInflater;
   private RelativeLayout mBarView;
   private ImageView mLogoView;
   // private View mHomeView;
   private TextView mTitleView;
   private LinearLayout mActionsView;
   private ImageButton mHomeBtn;
   private RelativeLayout mHomeLayout;
   
   private boolean mIsInitialised = false;

   public ActionBar(Context _context, AttributeSet _attrs) {
      super(_context, _attrs);

      mInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      mBarView = (RelativeLayout) mInflater.inflate(R.layout.actionbar, null);
      addView(mBarView);

      mLogoView = (ImageView) mBarView.findViewById(R.id.actionbar_home_logo);
      mHomeLayout = (RelativeLayout) mBarView.findViewById(R.id.actionbar_home_bg);
      mHomeBtn = (ImageButton) mBarView.findViewById(R.id.actionbar_home_btn);

      mTitleView = (TextView) mBarView.findViewById(R.id.actionbar_title);
      mActionsView = (LinearLayout) mBarView.findViewById(R.id.actionbar_actions);
   }
   
   public void setInitialised(boolean isInitialised) {
      this.mIsInitialised = isInitialised;
   }

   public boolean isInitialised() {
      return mIsInitialised;
   }

   public void setHomeAction(IntentAction action) {
      mHomeBtn.setOnClickListener(this);
      mHomeBtn.setTag(action);
      mHomeBtn.setImageResource(action.getDrawable());
      mHomeLayout.setVisibility(View.VISIBLE);
   }

   /**
    * Shows the provided logo to the left in the action bar.
    * 
    * This is ment to be used instead of the setHomeAction and does not draw a
    * divider to the left of the provided logo.
    * 
    * @param _resId
    *           The drawable resource id
    */
   public void setHomeLogo(int _resId) {
      // TODO: Add possibility to add an IntentAction as well.
      mLogoView.setImageResource(_resId);
      mLogoView.setVisibility(View.VISIBLE);
      mHomeLayout.setVisibility(View.GONE);
   }

   public void setTitle(CharSequence _title) {
      mTitleView.setText(_title);
   }

   public void setTitle(int _resid) {
      mTitleView.setText(_resid);
   }

   @Override
   public void onClick(View _view) {
      final Object tag = _view.getTag();
      if (tag instanceof Action) {
         final Action action = (Action) tag;
         action.performAction();
      }
   }

   /**
    * Adds a list of {@link Action}s.
    * 
    * @param _actionList
    */
   public void addActions(ActionList _actionList) {
      int actions = _actionList.size();
      for (int i = 0; i < actions; i++) {
         addAction(_actionList.get(i));
      }
   }

   /**
    * Adds a new {@link Action}.
    * 
    * @param _action
    *           the action to add
    */
   public void addAction(Action _action) {
      final int index = mActionsView.getChildCount();
      addAction(_action, index);
   }

   /**
    * Adds a new {@link Action} at the specified index.
    * 
    * @param _action
    *           the action to add
    */
   public void addAction(Action _action, int _index) {
      mActionsView.addView(inflateAction(_action), _index);
   }

   /**
    * Inflates a {@link View} with the given {@link Action}.
    * 
    * @param _action
    *           the action to inflate
    * @return a view
    */
   private View inflateAction(Action _action) {
      View view = mInflater.inflate(R.layout.actionbar_item, mActionsView, false);

      ImageButton labelView = (ImageButton) view.findViewById(R.id.actionbar_item);
      labelView.setImageResource(_action.getDrawable());

      view.setTag(_action);
      view.setOnClickListener(this);
      return view;
   }



   /**
    * A {@link LinkedList} that holds a list of {@link Action}s.
    */
   public static class ActionList extends LinkedList<Action> {
      private static final long serialVersionUID = -2733421682894137420L;
   }

   /**
    * Definition of an action that could be performed, along with a icon to
    * show.
    */
   public interface Action {
      public int getDrawable();

      public void performAction();
   }

   public static abstract class AbstractAction implements Action {
      final private int mDrawable;

      public AbstractAction(int _drawable) {
         mDrawable = _drawable;
      }

      @Override
      public int getDrawable() {
         return mDrawable;
      }
   }

   public static class IntentAction extends AbstractAction {
      private Context mContext;
      private Intent mIntent;

      public IntentAction(Context _context, Intent _intent, int _drawable) {
         super(_drawable);
         mContext = _context;
         mIntent = _intent;
      }

      @Override
      public void performAction() {
         try {
            if (mIntent != null) {
               mContext.startActivity(mIntent);
            }
            else{
               ((Activity)mContext).finish();
            }
         } catch (ActivityNotFoundException e) {
            Toast.makeText(mContext, mContext.getText(R.string.actionbar_activity_not_found),
                  Toast.LENGTH_SHORT).show();
         }
      }
   }

   /*
    * public static abstract class SearchAction extends AbstractAction { public
    * SearchAction() { super(R.drawable.actionbar_search); } }
    */
}
