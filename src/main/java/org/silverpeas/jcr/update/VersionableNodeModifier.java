package org.silverpeas.jcr.update;

import org.apache.jackrabbit.oak.api.Type;
import org.apache.jackrabbit.oak.spi.commit.CommitHook;
import org.apache.jackrabbit.oak.spi.commit.CommitInfo;
import org.apache.jackrabbit.oak.spi.state.NodeState;
import org.jetbrains.annotations.NotNull;

import javax.jcr.nodetype.NodeType;
import java.util.ArrayList;
import java.util.List;

import static org.apache.jackrabbit.JcrConstants.JCR_MIXINTYPES;

/**
 * In Silverpeas, the versioned nodes in the JCR are set with the
 * {@link NodeType#MIX_SIMPLE_VERSIONABLE} mixin but Apache Jackrabbit Oak expects the versioned
 * nodes to be set with the {@link NodeType#MIX_VERSIONABLE} mixin. The goal of this modifier is to
 * set for each versioned nodes encountered in the Silverpeas JCR the latter mixin.
 * @author mmoquillon
 */
@SuppressWarnings("unused")
public class VersionableNodeModifier implements CommitHook {
  @Override
  public @NotNull NodeState processCommit(final NodeState before,
      final NodeState after, final CommitInfo info) {
    List<String> mixins = new ArrayList<>();
    var mixinTypes = before.getNames(JCR_MIXINTYPES);
    for (String mixinType : mixinTypes) {
      if (mixinType.equals(NodeType.MIX_SIMPLE_VERSIONABLE)) {
        mixins.add(NodeType.MIX_VERSIONABLE);
      } else {
        mixins.add(mixinType);
      }
    }

    return after.builder()
        .setProperty(JCR_MIXINTYPES, mixins, Type.NAMES)
        .getNodeState();
  }
}
