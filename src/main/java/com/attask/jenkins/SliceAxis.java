package com.attask.jenkins;

import hudson.Extension;
import hudson.matrix.AxisDescriptor;
import hudson.matrix.Messages;
import hudson.matrix.TextAxis;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.List;

/**
 * User: Joel Johnson
 * Date: 1/26/13
 * Time: 6:44 PM
 */
public class SliceAxis extends TextAxis {
	@DataBoundConstructor
	public SliceAxis(String name, Integer value) {
		super(name, expand(value));
	}

	private static String expand(Integer value) {
		if(value == null) {
			value = 1;
		}

		if(value <= 0) {
			throw new IllegalArgumentException("value must be positive but was: " + value);
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= value; i++) {
			sb.append(i).append('-').append(value).append(' ');
		}
		return sb.toString().trim();
	}

	@Extension
	public static class DescriptorImpl extends AxisDescriptor {
		@Override
		public String getDisplayName() {
			return "Slice Axis";
		}
	}
}
