/*
 * Copyright (c) 2017-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.litho.config;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;

import com.facebook.litho.BuildConfig;
import com.facebook.yoga.YogaLogger;

/**
 * Configuration for the Components library.
 */
public class ComponentsConfiguration {

  public static YogaLogger YOGA_LOGGER;

  /**
   * Indicates whether this is an internal build. Note that the implementation
   * of <code>BuildConfig</code> that this class is compiled against may not be
   * the one that is included in the
   * APK. See: <a
   * href="http://facebook.github.io/buck/rule/android_build_config.html">android_build_config</a>.
   */
  public static final boolean IS_INTERNAL_BUILD = BuildConfig.IS_INTERNAL_BUILD;

  /** Indicates that the incremental mount helper is required for this build. */
  public static final boolean USE_INCREMENTAL_MOUNT_HELPER =
      BuildConfig.USE_INCREMENTAL_MOUNT_HELPER;

  /** Whether transitions are supported for this API version. */
  public static final boolean ARE_TRANSITIONS_SUPPORTED = (SDK_INT >= ICE_CREAM_SANDWICH);

  /**
   * Option to enabled debug mode. This will save extra data asscociated with each node and allow
   * more info about the hierarchy to be retrieved. Used to enable stetho integration. It is highly
   * discouraged to enable this in production builds. Due to how the Litho releases are distributed
   * in open source IS_INTERNAL_BUILD will always be false. It is therefore required to override
   * this value using your own application build configs. Recommended place for this is in a
   * Application subclass onCreate() method.
   */
  public static boolean isDebugModeEnabled = IS_INTERNAL_BUILD;

  /**
   * Debug option to highlight interactive areas in mounted components.
   */
  public static boolean debugHighlightInteractiveBounds = false;

  /**
   * Debug option to highlight mount bounds of mounted components.
   */
  public static boolean debugHighlightMountBounds = false;

  /**
   * Populates additional metadata to find mounted components at runtime. Defaults to the presence
   * of an <pre>IS_TESTING</pre> system property at startup but can be overridden at runtime.
   */
  public static boolean isEndToEndTestRun = System.getProperty("IS_TESTING") != null;

  /**
   * Use the new bootstrap ranges code instead of initializing all the items when the binder view is
   * measured (t12986103).
   */
  public static boolean bootstrapBinderItems = false;

  /**
   * Whether to use Object pooling via {@link com.facebook.litho.ComponentsPools}. This is switch
   * because we are experimenting with turning off pooling to get a sense of what its impact is in
   * production.
   */
  public static volatile boolean usePooling = true;

  /**
   * Whether we unmount children of the views implementing {@link
   * com.facebook.litho.HasLithoViewChildren} when unmounting those views themselves. This is for
   * experimentation purposes to see the impact of this change on different product surfaces.
   */
  public static boolean deepUnmountEnabled = true;

  /**
   * Whether incremental mount should use the local visible bounds of the {@link
   * com.facebook.litho.LithoView}.
   */
  public static boolean incrementalMountUsesLocalVisibleBounds = false;

  /**
   * Whether incremental mount that begins in {@link com.facebook.litho.LithoView} should use the
   * local visible bounds of the view.
   */
  public static boolean lithoViewIncrementalMountUsesLocalVisibleBounds = false;

  /**
   * Whether to keep a reference to the InternalNode tree in LayoutState instead of immediately
   * releasing it.
   */
  public static boolean persistInternalNodeTree = false;

  /**
   * Whether to store the full list of Components used to create an {@link
   * com.facebook.litho.InternalNode} when persisting the InternalNode tree.
   */
  public static boolean persistAllComponents = false;

  /** Whether the RecyclerCollectionComponent can asynchronously set the root of a SectionTree. */
  public static boolean setRootAsyncRecyclerCollectionComponent = false;

  /**
   * If false, we preallocate all mount specs in a ComponentTree if it sets a preallocation handler.
   * If true, we use {@link com.facebook.litho.annotations.MountSpec#canPreallocate()} to determine
   * for each MountSpec if it should be preallocated or not.
   */
  public static boolean preallocatePerMountSpec = false;

  /**
   * If true, the ComponentTree provides a default handler for performing preallocation if none is
   * provided. If false, the ComponentTree will not perform preallocation if no handler is provided
   * to the ComponentTree.Builder. This is overriden to false if preallocatePerMountSpec is false.
   */
  private static boolean defaultPreallocateMountContentHandler;

  public static void setDefaultPreallocateMountContentHandler(boolean enableDefault) {
    defaultPreallocateMountContentHandler = enableDefault;
  }

  public static boolean getDefaultPreallocateMountContentHandler() {
    return defaultPreallocateMountContentHandler && preallocatePerMountSpec;
  }

  /**
   * Don't call RecyclerView.Adapter#notifyItemChanged/notifyItemRangeChanged for rows rendered with
   * components in sections if this is true.
   */
  public static boolean sectionsNoNotifyItemChanged = false;

  /**
   * If true, insert operations with the {@link com.facebook.litho.widget.RecyclerBinder} will not
   * start async layout calculations for the items in range, instead these layout calculations will
   * be posted to the next frame.
   */
  public static boolean insertPostAsyncLayout = false;

  /**
   * If true, the components mKey and mChildCounters will not be initialized at construction time.
   */
  public static boolean lazyInitializeComponent = false;

  /** If true, the ComponentsAccessibilityDelegate will be lazily initialized. */
  public static boolean lazyInitializeComponentAccessibilityDelegate = false;

  /**
   * If false, global keys will not be generated (litho level state updates won't work). It's highly
   * discouraged to to change this to false, unless you handle all your updates outside of the litho
   * framework
   */
  public static boolean useGlobalKeys = true;
}
